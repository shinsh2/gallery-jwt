package kr.co.wikibook.gallery.common.config;
import java.sql.SQLException;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;
import com.p6spy.engine.spy.P6SpyOptions;

public class P6SpyEventListener extends JdbcEventListener {

    @Override
    public void onAfterGetConnection(ConnectionInformation connectionInformation, SQLException e) {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettySqlFormatter.class.getName());
    }
}