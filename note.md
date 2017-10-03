##第七章

####1. manage/product/save.do 更新导致create_time置空

http://localhost:8088/manage/product/save.do?categoryId=1&name=三星洗衣机&subtitle=三星大促销&subImages=test.jpg&detail=detailtext&price=1000&stock=100&status=1&id=34

需要删除`ProductMapper.java` -> `updateByPrimaryKey`中的

	create_time = #{createTime,jdbcType=TIMESTAMP},

否则会导致每次都更新将create_time置为null

####2. /manage/product/set_sale_status.do  if判断导致update_time不更新

需要修改`ProductMapper.java` -> `updateByPrimaryKeySelective`中的

	<if test="updateTime != null" >
		update_time = now(),
	</if>

修改为

	update_time = now(),

否则会导致update_time不更新
