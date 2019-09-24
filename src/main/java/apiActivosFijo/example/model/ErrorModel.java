package apiActivosFijo.example.model;

import java.util.Map;

/**
 * The type Error model.
 */
public class ErrorModel {

    private int code;
    private Map<String, String> description;
    private String id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public Map<String, String> getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(Map<String, String> description) {
        this.description = description;
    }
}