package app

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface CityMapper {

    @Select("""
        SELECT
        id, name, state, country
        FROM city
        WHERE
        /*%if country != null */
        country = /* country */'US'
        /*%end*/
        /*%if state != null */
        AND state = /* state */'CA'
        /*%end*/
    """)
    fun find(@Param("state") state: String?, @Param("country") country: String?): City
}
