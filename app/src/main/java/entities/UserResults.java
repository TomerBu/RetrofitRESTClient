package entities;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResults {

    private Integer totalCount;
    private Boolean incompleteResults;
    @SerializedName("items")
    private List<GitUser> gitUsers = new ArrayList<GitUser>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     *
     * @param totalCount
     * The total_count
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     *
     * @return
     * The incompleteResults
     */
    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    /**
     *
     * @param incompleteResults
     * The incomplete_results
     */
    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    /**
     *
     * @return
     * The gitUsers
     */
    public List<GitUser> getGitUsers() {
        return gitUsers;
    }

    /**
     *
     * @param gitUsers
     * The gitUsers
     */
    public void setGitUsers(List<GitUser> gitUsers) {
        this.gitUsers = gitUsers;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


