# elasticsearchTool

场景：
es 中point 是特殊类型，但是mysql 中分成了两个字段。

1.写了一个拦截器MyPlugin2 拦截查询结构
2.Point 注解用在 导入到es 中对应的字段

eg：
   @Transient
    @Point(lon = "longitude", lat = "latitude")
    private Location point;
    
    lon的值 是经度对应于实体类中的字段  lat 是纬度对应的字段
    
    
