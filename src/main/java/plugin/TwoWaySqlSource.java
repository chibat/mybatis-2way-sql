package plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import plugin.TwoWaySqlParser.Result;

/**
 * @author tomofumi
 */
public class TwoWaySqlSource implements SqlSource {

    private final Configuration configuration;
    private final String script;

    public TwoWaySqlSource(Configuration configuration, String script) {
        this.configuration = configuration;
        this.script = script;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {

        Map<String, Object> map = null;
        if (parameterObject != null) {
            if (parameterObject instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> mapTemp = (Map<String, Object>) parameterObject;
                map = mapTemp;
            } else {
                throw new UnsupportedOperationException();
            }
        } else {
            map = new HashMap<>();
        }

        final TwoWaySqlParser parser = new TwoWaySqlParser(script);
        map.forEach((name, value) -> {
            @SuppressWarnings("unchecked") final Class<Object> clazz = (Class<Object>) value.getClass();
            parser.addValue(name, clazz, value);
        });

        final Result result = parser.execute();
        final List<ParameterMapping> parameterMappings = new ArrayList<>();

        for (int i = 0; i < result.params.size(); i++) {
            final String property = "_P" + i;
            parameterMappings
                    .add(new ParameterMapping.Builder(configuration, property, result.params.get(i).type).build());
            map.put(property, result.params.get(i).value);
        }

        return new BoundSql(configuration, result.sql, parameterMappings, parameterObject);
    }

}
