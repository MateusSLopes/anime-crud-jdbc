package anime_store.crud.repository;

import anime_store.crud.conn.ConnectionFactory;
import anime_store.crud.domain.Anime;
import anime_store.crud.domain.Producer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class AnimeRepository {
    public static void save(Anime anime) {
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = savePreparedStatement(conn, anime)) {
            ps.execute();
            log.info("Inserted anime in the database '{}'", anime.getName());
        } catch (SQLException e) {
            log.error("Error while trying to insert anime '{}'", anime.getName(), e);
        }
    }

    private static PreparedStatement savePreparedStatement(Connection conn, Anime anime) throws SQLException {
        String sql = "INSERT INTO `anime_store`.`anime` (`name`,`episodes`,`producer_id`) VALUES (?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, anime.getName());
        ps.setInt(2, anime.getEpisodes());
        ps.setInt(3, anime.getProducer().getId());
        return ps;
    }

    public static void update(Anime anime) {
        log.info("Updating anime");
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = updatePreparedStatement(conn, anime)) {
            ps.execute();
            log.info("Updated anime in the database '{}'", anime.getName());
        } catch (SQLException e) {
            log.error("Error while trying to update anime with ID: '{}'", anime.getId(), e);
        }
    }

    public static PreparedStatement updatePreparedStatement(Connection conn, Anime anime) throws SQLException {
        String sql = "UPDATE `anime_store`.`anime` SET `name` = ?, `episodes` = ? WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, anime.getName());
        ps.setInt(2, anime.getEpisodes());
        ps.setInt(3, anime.getId());
        return ps;
    }

    public static void delete(Integer id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = deletePreparedStatement(conn, id)) {
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to delete anime '{}'", id, e);
        }
    }

    private static PreparedStatement deletePreparedStatement(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `anime_store`.`anime` WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static List<Anime> findByName(String name) {
        List<Anime> animes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = findByNamePreparedStatement(conn, name);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                animes.add(getAnime(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animes;
    }

    private static PreparedStatement findByNamePreparedStatement(Connection conn, String name) throws SQLException {
        String sql = """
                SELECT a.id, a.name, a.episodes, a.producer_id, p.name as 'producer_name' FROM anime_store.anime a inner join
                anime_store.producer p on a.producer_id = p.id
                WHERE a.name LIKE ?;
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "%" + name + "%");
        return ps;
    }

    public static Optional<Anime> findById(Integer id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = findByIdPreparedStatement(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) return Optional.empty();
            return Optional.of(getAnime(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PreparedStatement findByIdPreparedStatement(Connection conn, Integer id) throws SQLException {
        String sql = """
                SELECT a.id, a.name, a.episodes, a.producer_id, p.name as 'producer_name' FROM anime_store.anime a inner join
                anime_store.producer p on a.producer_id = p.id
                WHERE a.id = ?;
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    private static Anime getAnime(ResultSet rs) throws SQLException {
        var producer = Producer.builder().name(rs.getString("producer_name")).id(rs.getInt("producer_id")).build();
        return Anime.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .episodes(rs.getInt("episodes"))
                .producer(producer)
                .build();
    }
}
