package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TodoItem {
    private String title;
    private String desc;
    private String dateString;
    private Date current_date;
    private String category;
    private String due_date;

    public TodoItem(String title, String desc, String category, String due_date){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.dateString = format.format(current_date);
        this.category = category;
        this.due_date = due_date;
    }
    
    public TodoItem(String title, String desc, String dateString, String category, String due_date){
        this.title=title;
        this.desc=desc;
        this.dateString = dateString;
        this.category = category;
        this.due_date = due_date;
        try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.current_date = format.parse(dateString);
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = current_date;
    }

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ". [" + category + "] " + title + " - "+ desc + " - " + due_date + " - " + dateString;
	}
    
	public String toSaveString() {
		return "##" + category + "##" + title + "##" + desc + "##" + due_date + "##" + dateString + "\n" ;
	}
    
    
}
