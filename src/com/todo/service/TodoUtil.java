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
		
		String title, desc, category, due_date;
		int count;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n"
				+ "제목 > ");
		
		title = sc1.next();
		if (list.isDuplicate(title)) {
			System.out.print("제목이 중복 됩니다!");
			return;
		}	
		System.out.print("카테고리 > ");
		category = sc1.next().trim();
		
		System.out.print("내용 > ");
		desc = sc2.nextLine().trim();
		
		System.out.print("마감일자 > ");
		due_date = sc1.next().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if (list.addItem(t) > 0) {
			System.out.print("추가되었습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
				
		System.out.print("[항목삭제]\n"	+ "삭제할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.deleteItem(index) > 0) {
			System.out.println("삭제되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);

		System.out.print("[항목수정]\n"
				+ "수정할 항목의 번호을 입력하십시오 > "
			);
		int count = sc1.nextInt();
		
		System.out.print("새 제목 > ");
		String new_title = sc1.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복 됩니다!");
			return;
		}
		System.out.print("새 카테고리 > ");
		String new_category = sc1.next().trim();
		
		System.out.print("새 내용 > ");
		String new_description = sc2.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		String new_due_date = sc1.next().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(count);
		if(l.updateItem(t) > 0) {
			System.out.println("수정되었습니다.");
		}
		

	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword) ) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("\n총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void find_cate(TodoList l, String category) {
		int count = 0;
		for(TodoItem item : l.getListCategory(category) ) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("\n총 " + count + "개의 항목을 찾았습니다.");
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories() ) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n총 " + count + "개의 카테고리가 등록되어 있습니다.");
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
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String dateString = st.nextToken();
				TodoItem t = new TodoItem(title, desc, dateString, category, due_date);
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
