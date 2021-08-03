package com.management.employee.repository;

import com.management.employee.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcCommentsRepository implements  CommentsRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    private JdbcCommentsRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }


    @Override
    public int add(Comment comment) {
        return jdbc.update("INSERT INTO comments (REPORT_ID,COMMENT,AUTHOR_ID) VALUE (?,?,?)",
                comment.getReport_id(),
                comment.getComment(),
                comment.getAuthor());
    }

    @Override
    public Comment findById(int id) {
        return jdbc.queryForObject("SELECT COMMENT_ID,REPORT_ID,COMMENT,AUTHOR_ID,CREATION_DATE,UPDATE_DATE FROM comments WHERE COMMENT_ID=?",
                this::mapRowToComment, id);
    }

    @Override
    public Iterable<Comment> listAll() {
        return jdbc.query("SELECT COMMENT_ID,REPORT_ID,COMMENT,AUTHOR_ID,CREATION_DATE,UPDATE_DATE FROM comments",
                this::mapRowToComment);
    }

    @Override
    public Iterable<Comment> findByReportId(int id) {
        return jdbc.query("SELECT COMMENT_ID,REPORT_ID,COMMENT,AUTHOR_ID,CREATION_DATE,UPDATE_DATE FROM comments WHERE REPORT_ID=?",
                this::mapRowToComment, id);
    }

    @Override
    public Iterable<Comment> findByEmployeeId(int id) {
        return jdbc.query("SELECT COMMENT_ID,REPORT_ID,COMMENT,AUTHOR_ID,CREATION_DATE,UPDATE_DATE FROM comments WHERE AUTHOR_ID=?",
                this::mapRowToComment, id);
    }

    private Comment mapRowToComment(ResultSet rs, int rowNum) throws SQLException {
        return new Comment(rs.getInt("COMMENT_ID"),
                rs.getInt("REPORT_ID"),
                rs.getString("COMMENT"),
                rs.getInt("AUTHOR_ID"),
                rs.getDate("CREATION_DATE"),
                rs.getDate("UPDATE_DATE"));
    }
}
