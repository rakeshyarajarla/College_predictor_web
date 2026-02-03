package com.JDBC.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollegePredictorService {

    public List<College> findColleges(int rank, String gender, String branchCode, String categoryCode) {
        List<College> colleges = new ArrayList<>();
        String table = gender.equalsIgnoreCase("FEMALE") ? "college_details_girls" : "college_details_boys";

        // Map frontend category codes to DB column names if necessary,
        // or ensure frontend sends exact column names.
        // Based on user code: "OC", "SC", "ST", "BCA", "BCB", "BCC", "BCD", "BCE",
        // "OC_EWS"

        StringBuilder sql = new StringBuilder(
                "SELECT id, INSTCODE, COLLEGENAME, branch_code, COLLFEES " +
                        "FROM " + table +
                        " WHERE " + categoryCode + " >= ? ");

        if (!branchCode.equalsIgnoreCase("ALL")) {
            sql.append(" AND branch_code = ? ");
        }

        sql.append(" ORDER BY ").append(categoryCode).append(" ASC");

        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql.toString())) {

            ps.setInt(1, rank);

            if (!branchCode.equalsIgnoreCase("ALL")) {
                ps.setString(2, branchCode);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    College college = new College(
                            rs.getInt("id"),
                            rs.getString("INSTCODE"),
                            rs.getString("COLLEGENAME").replaceAll("\\s+", " ").trim(), // Clean up spaces
                            rs.getString("branch_code"),
                            rs.getDouble("COLLFEES"));
                    colleges.add(college);
                }
            }

        }catch (SQLException e) {
    e.printStackTrace();
    throw new RuntimeException(e);
}

        return colleges;
    }
}



