package anime_store.crud.repository;

import anime_store.crud.conn.ConnectionFactory;
import anime_store.crud.domain.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProducerRepository {
    public static void save(Producer producer) {
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = savePreparedStatement(conn, producer)) {
            ps.execute();
            log.info("Inserted producer in the database '{}'", producer.getName());
        } catch (SQLException e) {
            log.error("Error while trying to insert producer '{}'", producer.getName(), e);
        }
    }

    private static PreparedStatement savePreparedStatement(Connection conn, Producer producer) throws SQLException {
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES (?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, producer.getName());
        return ps;
    }

    public static void update(Producer producer) {
        log.info("Updating producer");
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = updatePreparedStatement(conn, producer)) {
            ps.execute();
            log.info("Updated producer in the database '{}'", producer.getName());
        } catch (SQLException e) {
            log.error("Error while trying to update producer with ID: '{}'", producer.getId(), e);
        }
    }

    public static PreparedStatement updatePreparedStatement(Connection conn, Producer producer) throws SQLException {
        String sql = "UPDATE `anime_store`.`producer` SET `name` = ? WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, producer.getName());
        ps.setInt(2, producer.getId());
        return ps;
    }

    public static void delete(Integer id) {
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = deletePreparedStatement(conn, id)) {
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to delete producer '{}'", id, e);
        }
    }

    private static PreparedStatement deletePreparedStatement(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `anime_store`.`producer` WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static List<Producer> findByName(String name) {
        List<Producer> producers = new ArrayList<Producer>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = findByNamePreparedStatement(conn, name);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                producers.add(getProducer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producers;
    }

    private static PreparedStatement findByNamePreparedStatement(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM anime_store.producer WHERE name LIKE ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + name + "%");
        return ps;
    }

    private static Producer getProducer(ResultSet rs) throws SQLException {
        return Producer.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}
