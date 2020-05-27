package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		String sql = "INSERT INTO department (Name) VALUES (?)";

		try {
			st = this.conn.prepareStatement(sql);
			st.setString(1, obj.getName());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {

			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		String sql = "UPDATE department " + "SET Name = ? WHERE id = ?";
		try {
			st = this.conn.prepareStatement(sql);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		String sql = "DELETE FROM department WHERE Id = ?";
				
		try {
			st = this.conn.prepareStatement(sql);
			st.setInt(1, id);
			
			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM department WHERE id = ?";
		try {
			st = this.conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			Department department = new Department();
			if (rs.next()) {
				department = instanciateDepartment(rs);
			}

			return department;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeResultSet(rs);
		}

	}

	private Department instanciateDepartment(ResultSet rs) throws SQLException {

		Department department = new Department();

		department.setId(rs.getInt("Id"));
		department.setName(rs.getString("Name"));

		return department;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM department";

		try {
			st = this.conn.prepareStatement(sql);
			rs = st.executeQuery();

			List<Department> departments = new ArrayList<>();
			while (rs.next()) {
				Department dp = new Department();

				dp = instanciateDepartment(rs);

				departments.add(dp);
			}

			return departments;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

}
