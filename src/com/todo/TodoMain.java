package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;



public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		
		
		boolean quit = false;
		
		Menu.displaymenu();
		//l.importData("todolist.txt");
		
		do {
			Menu.prompt();
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "del_multi":
				TodoUtil.deleteMultiItem(l);
				break;
				
			case "del_complete":
				TodoUtil.deleteCompleteItem(l);
				break;
				
			case "del_past":
				TodoUtil.deletePastItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "comp":
				int id = sc.nextInt();
				TodoUtil.completeItem(l, id);
				break;
			
			case "comp_multi":
				TodoUtil.completeMultiItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_cate": 
				TodoUtil.listCateAll(l);
				break;
				
			case "find": 
				String keyword = sc.nextLine().trim(); 
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate": 
				String cate = sc.nextLine().trim();
				TodoUtil.find_cate(l, cate);
				break;
				
			case "ls_name":
				System.out.println("��������� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("���񿪼����� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("��¥������ �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc": 
				System.out.println("��¥�������� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_comp": 
				TodoUtil.find_comp(l);
				break;
				
			case "ls_today": 
				TodoUtil.ListToday(l);
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("��Ȯ�� ��ɾ �Է��ϼ���. (���� - help)");
				break;
			}
			
		} while (!quit);
		
			
		
		
		
	}
}
