package com.todo.service;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedReader;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n"
				+ "제목 > ");
		
		title = sc1.next();
		if (list.isDuplicate(title)) {
			System.out.print("제목이 중복 됩니다!");
			return;
		}	
		
		System.out.print("내용 > ");
		desc = sc2.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.print("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
				
		System.out.print("[항목삭제]\n"	+ "삭제할 항목의 제목을 입력하시오 > ");
		String title = sc.next();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		System.out.print("삭제되었습니다.");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);

		System.out.print("[항목수정]\n"
				+ "수정할 항목의 제목을 입력하십시오 > "
			);
		String title = sc1.next().trim();
		
		if (!l.isDuplicate(title)) {
			System.out.println("없는 제목입니다.");
			return;
		}

		System.out.print("새 제목 > ");
		String new_title = sc1.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복 됩니다!");
			return;
		}
		
		System.out.print("새 내용 >  ");
		String new_description = sc2.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다.");
			}
		}
		

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			while ((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String dateString = st.nextToken();
				TodoItem t = new TodoItem(title, desc, dateString);
				l.addItem(t);
			}
			br.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("todolist.txt 파일이 없습니다.");
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
