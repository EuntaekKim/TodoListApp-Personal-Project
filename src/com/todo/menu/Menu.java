package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList 관리 명령어 사용법>");
        System.out.println("add - 항목 추가");
        System.out.println("del - 항목 삭제");
        System.out.println("del_multi - 여러 항목 삭제");
        System.out.println("del_complete - 완료 항목 삭제");
        System.out.println("del_past - 기한이 지난 항목 삭제");
        System.out.println("edit - 항목 수정");
        System.out.println("comp <번호> - 완료 체크");
        System.out.println("comp_multi - 여러 항목 완료 체크");
        System.out.println("ls - 전체 목록");
        System.out.println("ls_cate - 카테고리 목록");
        System.out.println("find <검색어> - 항목 검색");
        System.out.println("find_cate <검색어> - 카테고리 검색");
        System.out.println("ls_name - 제목순 정렬");
        System.out.println("ls_name_desc - 제목역순 정렬");
        System.out.println("ls_date - 날짜순 정렬");
        System.out.println("ls_date_desc - 날짜역순 정렬");
        System.out.println("ls_comp - 완료 항목 목록");
        System.out.println("ls_today - 오늘까지 해야 할 일 목록");
        System.out.println("help - 명령어 사용법 표시");
        System.out.println("exit - 종료");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}
