package Ser;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {
	
	//*************Attributes
    private static final long serialVersionUID = 1L;
    private String description;
    private boolean isCompleted;
    private Date creationDate;
    
  //*************Constructor
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
        this.creationDate = new Date(); // Set the creation date to the current date and time
    }

    
  //*************Methods
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {//*************Task to string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(creationDate);
        return (isCompleted ? "[X] " : "[ ] ") + description + " (Created: " + formattedDate + ")";
    }
}
