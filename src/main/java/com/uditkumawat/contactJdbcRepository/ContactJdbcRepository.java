package com.uditkumawat.contactJdbcRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uditkumawat.beans.Contact;

@Repository
public class ContactJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Contact findById(long id) {

		return jdbcTemplate.queryForObject("Select * from contacts where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Contact>(Contact.class));
	}

	public List<Contact> findAll() {
		return jdbcTemplate.query("Select * from contacts", new ContactRowMapper());
	}

	public int addContact(Contact contact) {
		return jdbcTemplate.update("INSERT INTO Contacts(name,email,phone) VALUES(?,?,?)", contact.getName(),
				contact.getEmail(), contact.getPhone());
	}

	public int deleteContact(Long id) {
		return jdbcTemplate.update("DELETE FROM contacts WHERE id = ?", new Object[] { id });
	}

	public int updateContact(Long id, Contact contact) {
		return jdbcTemplate.update("update contacts set name = ? ,email = ?, phone=? where id = ?", contact.getName(),
				contact.getEmail(), contact.getPhone(), id);
	}

	public List<Contact> getContactsByNameOrEmail(String searchValue, int limit, int offset) {
		StringBuilder sql = new StringBuilder();
		sql.append("Select * from contacts where name=? or email=? ");

		if (limit > 0) {
			sql.append("LIMIT ");

			if (offset > 0) {
				sql.append(offset);
				sql.append(", ");
			}

			sql.append(limit);
			sql.append(" ");
		}

		return jdbcTemplate.query(sql.toString(), new Object[] { searchValue, searchValue }, new ContactRowMapper());
	}

	public int countContacts() {
		return jdbcTemplate.queryForObject("select count(*) from contacts", Integer.class);
	}
}

class ContactRowMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

		Contact contact = new Contact();
		contact.setId(rs.getInt("id"));
		contact.setName(rs.getString("name"));
		contact.setEmail(rs.getString("email"));
		contact.setPhone(rs.getString("phone"));
		return contact;
	}
}
