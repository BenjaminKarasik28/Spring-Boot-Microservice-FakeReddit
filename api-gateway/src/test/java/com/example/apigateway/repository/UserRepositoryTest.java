package com.example.apigateway.repository;

import com.example.apigateway.Bean.UserBean;
import com.example.apigateway.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertEquals;
@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
    @InjectMocks
    private UserRepository userRepository;
    @Mock
    private JdbcTemplate jdbc;
    @Mock
    private ResultSet rs;
    @Captor
    private ArgumentCaptor<RowMapper<UserBean>> rowMapperCaptor;
    private UserBean user;
    @Before
    public void test()throws SQLException {
        user = new UserBean(1L, "batman", "pass");
        when(rs.getLong(any())).thenReturn(user.getId());
        when(rs.getString(any())).thenReturn(user.getUsername(), user.getPassword());
    }
    @Test
    public void getUserByUserName_SUCCESS() throws SQLException {
        userRepository.findByUsername("batman");
        verify(jdbc).queryForObject(anyString(), any(), rowMapperCaptor.capture());
        RowMapper<UserBean> rm = rowMapperCaptor.getValue();
        UserBean test2 = rm.mapRow(rs, 1);
        assertEquals(test2.getUsername(), user.getUsername());
        assertEquals(test2.getPassword(), user.getPassword());
        assertEquals(test2.getId(), user.getId());
    }
}