package com.todo.service;

import java.text.SimpleDateFormat;
import java.util.*;


import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date, time;
		int importance;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n"
				+ "제목 > ");
		
		title = sc1.nextLine().trim();
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
		
		System.out.print("소요시간 > ");
		time = sc1.nextLine().trim();
		sc1.nextLine();
		
		System.out.print("중요도 > ");
		importance = sc1.nextInt();
		
		
		TodoItem t = new TodoItem(title, desc, category, due_date, time, importance);
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
	
	public static void deleteMultiItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		int index;	
		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println("[여러 항목 삭제]");
		do {
			System.out.print("삭제할 항목의 번호를 입력하시오(0 입력 시 종료) > ");
			index = sc.nextInt();
			if(index != 0) {
				list.add(index);
			}
		} while (index != 0);
		int count = 0;
		for(int i : list) {
			if(l.deleteItem(i) > 0) {
				count++;
			} else {
				System.out.println(i + "번째 항목을 삭제할 수 없습니다.");
				break;
			}
		}
		System.out.println(count + "개의 항목을 삭제했습니다.");
		
	}
	
	public static void deletePastItem(TodoList l) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		String today = format.format(now);
		int count = 0;

		for(TodoItem item : l.getList()) {
			if(today.compareTo(item.getDue_date()) > 0) {
				if(l.deleteItem(item.getId()) > 0) {
					count++;
				}
			}
		}
		System.out.println(count + "개의 기간이 지난 항목을 삭제했습니다.");
	}
	
	public static void deleteCompleteItem(TodoList l) {
			l.deleteCompleteItem();
			System.out.print("완료 체크 된 항목들을 삭제했습니다.");
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
		
		System.out.print("새 소요시간 > ");
		String new_time = sc1.next().trim();
		
		System.out.print("새 중요도 > ");
		int new_importance = sc1.nextInt();
		
		
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, new_time, new_importance);
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
	
	public static void ListToday(TodoList l) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		String today = format.format(now);
		int count = 0;
		for(TodoItem item : l.getListToday(today)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println(count + "개의 오늘까지 해야 할 일을 찾았습니다.");
	}
	
	public static void find_comp(TodoList l) {
		int count = 0;
		for(TodoItem item : l.getListComplete()) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("\n총 " + count + "개의 항목이 완료되었습니다.");
	}
	
	public static void completeItem(TodoList l, int id) {
		for(TodoItem item : l.getList()) {
			if(item.getId() == id) {
				item.setIs_complete(1);
				l.updateItem(item);
			}
		}
		System.out.println("완료 체크하였습니다.");
	}
	
	public static void completeMultiItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		int index;
		ArrayList<Integer> list = new ArrayList<Integer>();
		do {
			System.out.print("완료체크 할 항목의 번호를 입력하십시오 (0 입력 시 종료) > ");
			index = sc.nextInt();
			if(index != 0) {
				list.add(index);
			}
		} while (index != 0);
		for(int id : list) {
			l.completeItem(id);
		}
		System.out.println(list.size() + "개의 항목을 완료 체크하였습니다.");
	}
	
	
	
}
