package kaspersky;

import org.junit.Test;
import utils.DBUtils;

import static base.IBaseEntity.waitForList;
import static utils.DBUtils.getQueryMysql;

public class DBTest {
    private static final String SQL_QUERY1 = "SELECT * FROM database.products";
    private static final String SQL_QUERY = "SELECT * FROM database.products WHERE idnew_table = ?";


    @Test
    public void DBQueeringTest() {
        System.out.println(waitForList("Sql error", () -> getQueryMysql(SQL_QUERY1)));
        System.out.println(DBUtils.getParametrizedQueryMysql(SQL_QUERY, "8"));
    }

}
