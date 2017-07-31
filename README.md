## Greendao Demo使用

1. 建表
```java
@Entity
public class StudentMsgBean {
    @Id
    private Long id;
    @Property(nameInDb = "STUDENTNUM")
    private String studentNum;
    @Property(nameInDb = "NAME")
    private String name;

    @Generated
    public StudentMsgBean(Long id, String studentNum, String name) {
        this.id = id;
        this.studentNum = studentNum;
        this.name = name;
    }

    @Generated
    public StudentMsgBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentMsgBean{" +
                "id=" + id +
                ", studentNum='" + studentNum + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
```
> @Id：主键 Long 型，可以通过@Id(autoincrement = true)设置自增长
  @Property：设置一个非默认关系映射所对应的列名，默认是使用字段名，例如：@Property(nameInDb = "name")
  @NotNull：设置数据库表当前列不能为空
  @Transient：添加此标记后不会生成数据库表的列




