package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TodoItem {
	private int id;
    private String title;
    private String desc;
    private String dateString;
    private Date current_date;
    private String category;
    private String due_date;
    private String time;
    private int is_complete;
    private int importance;
    

    public TodoItem(String title, String desc, String category, String due_date, String time, int is_complete, int importance){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.dateString = format.format(current_date);
        this.category = category;
        this.due_date = due_date;
        this.time = time;
        this.importance = importance;
        this.is_complete = is_complete;
    }
    
    public TodoItem(String title, String desc, String category, String due_date, String time, int importance){
        this.title=title;
        this.desc=desc;
        this.current_date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.dateString = format.format(current_date);
        this.category = category;
        this.due_date = due_date;
        this.time = time;
        this.importance = importance;
        this.is_complete = 0;
    }
    
    public TodoItem(String title, String desc, String dateString, String category, String due_date, String time, int is_complete, int importance){
        this.title=title;
        this.desc=desc;
        this.dateString = dateString;
        this.category = category;
        this.due_date = due_date;
        this.time = time;
        this.importance = importance;
        this.is_complete = is_complete;
        try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.current_date = format.parse(dateString);
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
    }
    
    public TodoItem(int id, String title, String desc, String dateString, String category, String due_date, String time, int is_complete, int importance){
    	this.id = id;
        this.title=title;
        this.desc=desc;
        this.dateString = dateString;
        this.category = category;
        this.due_date = due_date;
        this.time = time;
        this.importance = importance;
        this.is_complete = is_complete;
        try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.current_date = format.parse(dateString);
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getIs_complete() {
		return is_complete;
	}

	public void setIs_complete(int is_complete) {
		this.is_complete = is_complete;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String complete;
		if(is_complete == 1) {
			complete = "[V]";
		} else {
			complete = "";
		}
		return id + " [" + category + "] " + title + complete + " - "+ desc + " - " + due_date + " - " + dateString + " - 소요시간 : " + time + " - 중요도(1~10) : " + importance;
	}
    
	public String toSaveString() {
		return "##" + category + "##" + title + "##" + desc + "##" + due_date + "##" + dateString + "\n" ;
	}
    
    
}
