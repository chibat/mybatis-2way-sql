package mybatis.scripting.twowaysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.sql.DataSource;

import org.seasar.doma.internal.expr.ExpressionEvaluator;
import org.seasar.doma.internal.expr.Value;
import org.seasar.doma.internal.jdbc.sql.NodePreparedSqlBuilder;
import org.seasar.doma.internal.jdbc.sql.SqlParser;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.ConfigSupport;
import org.seasar.doma.jdbc.InParameter;
import org.seasar.doma.jdbc.PreparedSql;
import org.seasar.doma.jdbc.SqlKind;
import org.seasar.doma.jdbc.SqlLogType;
import org.seasar.doma.jdbc.SqlNode;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.StandardDialect;

import mybatis.scripting.twowaysql.TwoWaySqlParser.Result.Param;

/**
 */
public class TwoWaySqlParser {

    private static Config config = new DefaultConfig();
    private final SqlNode node;
    private final Map<String, Value> variableValues = new HashMap<>();

    public TwoWaySqlParser(String sql) {
        node = new SqlParser(sql).parse();
    }

    public <T> void addValue(String name, Class<T> type, T value) {
        variableValues.put(name, new Value(type, value));
    }

    public Result execute() {
        final PreparedSql preparedSql =
                new NodePreparedSqlBuilder(config, SqlKind.SCRIPT, null,
                        new ExpressionEvaluator(variableValues, config.getDialect().getExpressionFunctions(),
                                ConfigSupport.defaultClassHelper), // TODO to default
                        SqlLogType.NONE) // TODO SqlLogType to variable ？
                        .build(node, Function.identity()); // TODO Function.identity
        return convert(preparedSql);
    }

    private Result convert(PreparedSql preparedSql) {
        final Result result = new Result(preparedSql.getRawSql());
        for (final InParameter<?> parameter : preparedSql.getParameters()) {
            result.params.add(new Param(parameter.getValue(), parameter.getWrapper().getBasicClass()));
        }
        return result;
    }

    public static class Result {
        public final String sql;
        public final List<Param> params = new ArrayList<>();

        public Result(final String sql) {
            this.sql = sql;
        }

        public static class Param {
            public final Object value;
            public final Class<?> type;

            public Param(Object value, Class<?> type) {
                this.value = value;
                this.type = type;
            }
        }
    }

    private static class DefaultConfig implements Config {
        private static Dialect dialect = new StandardDialect();

        @Override
        public DataSource getDataSource() {
            return null;
        }

        @Override
        public Dialect getDialect() {
            return dialect;
        }
    }
}