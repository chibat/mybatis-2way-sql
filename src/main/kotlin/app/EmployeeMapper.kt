package app

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface EmployeeMapper {

    @Select("""
        SELECT *
        FROM employee
        WHERE id = /* id */99
    """)
    fun findById(@Param("id") id: Integer): Employee

     @Select("""
         SELECT *
         FROM employee
         WHERE id IN /* idList */(1,2,3)
    """)
    fun findByIdList(@Param("id") idList: List<Integer>): List<Employee>

}