package pl.dritms.model;

public class Behaviour {

    public static final String TABLE_NAME = "behaviour";

    private long id;
    private long roleId;
    private String name;
    private String description;

    public Behaviour() {
    }

    public Behaviour(long id, long roleId, String name, String description) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.description = description;
    }

    public Behaviour(String name, String description, long roleId) {
        this.name = name;
        this.description = description;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.description + ")";
    }
}
