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
		
		System.out.print("[�׸� �߰�]\n"
				+ "���� > ");
		
		title = sc1.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.print("������ �ߺ� �˴ϴ�!");
			return;
		}	
		System.out.print("ī�װ� > ");
		category = sc1.next().trim();
		
		System.out.print("���� > ");
		desc = sc2.nextLine().trim();
		
		System.out.print("�������� > ");
		due_date = sc1.next().trim();
		
		System.out.print("�ҿ�ð� > ");
		time = sc1.nextLine().trim();
		sc1.nextLine();
		
		System.out.print("�߿䵵 > ");
		importance = sc1.nextInt();
		
		
		TodoItem t = new TodoItem(title, desc, category, due_date, time, importance);
		if (list.addItem(t) > 0) {
			System.out.print("�߰��Ǿ����ϴ�.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
				
		System.out.print("[�׸����]\n"	+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		if(l.deleteItem(index) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
	}
	
	public static void deleteMultiItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		int index;	
		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println("[���� �׸� ����]");
		do {
			System.out.print("������ �׸��� ��ȣ�� �Է��Ͻÿ�(0 �Է� �� ����) > ");
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
				System.out.println(i + "��° �׸��� ������ �� �����ϴ�.");
				break;
			}
		}
		System.out.println(count + "���� �׸��� �����߽��ϴ�.");
		
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
		System.out.println(count + "���� �Ⱓ�� ���� �׸��� �����߽��ϴ�.");
	}
	
	public static void deleteCompleteItem(TodoList l) {
			l.deleteCompleteItem();
			System.out.print("�Ϸ� üũ �� �׸���� �����߽��ϴ�.");
		}


	public static void updateItem(TodoList l) {
		
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
	
		System.out.print("[�׸����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻʽÿ� > "
			);
		int count = sc1.nextInt();
		
		System.out.print("�� ���� > ");
		String new_title = sc1.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ� �˴ϴ�!");
			return;
		}
		System.out.print("�� ī�װ� > ");
		String new_category = sc1.next().trim();
		
		System.out.print("�� ���� > ");
		String new_description = sc2.nextLine().trim();
		
		System.out.print("�� �������� > ");
		String new_due_date = sc1.next().trim();
		
		System.out.print("�� �ҿ�ð� > ");
		String new_time = sc1.next().trim();
		
		System.out.print("�� �߿䵵 > ");
		int new_importance = sc1.nextInt();
		
		
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, new_time, new_importance);
		t.setId(count);
		if(l.updateItem(t) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
		
	
	}

	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
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
		System.out.println("\n�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void find_cate(TodoList l, String category) {
		int count = 0;
		for(TodoItem item : l.getListCategory(category) ) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("\n�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories() ) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n�� " + count + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
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
		System.out.println(count + "���� ���ñ��� �ؾ� �� ���� ã�ҽ��ϴ�.");
	}
	
	public static void find_comp(TodoList l) {
		int count = 0;
		for(TodoItem item : l.getListComplete()) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("\n�� " + count + "���� �׸��� �Ϸ�Ǿ����ϴ�.");
	}
	
	public static void completeItem(TodoList l, int id) {
		for(TodoItem item : l.getList()) {
			if(item.getId() == id) {
				item.setIs_complete(1);
				l.updateItem(item);
			}
		}
		System.out.println("�Ϸ� üũ�Ͽ����ϴ�.");
	}
	
	public static void completeMultiItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		int index;
		ArrayList<Integer> list = new ArrayList<Integer>();
		do {
			System.out.print("�Ϸ�üũ �� �׸��� ��ȣ�� �Է��Ͻʽÿ� (0 �Է� �� ����) > ");
			index = sc.nextInt();
			if(index != 0) {
				list.add(index);
			}
		} while (index != 0);
		for(int id : list) {
			l.completeItem(id);
		}
		System.out.println(list.size() + "���� �׸��� �Ϸ� üũ�Ͽ����ϴ�.");
	}
	
	
	
}
