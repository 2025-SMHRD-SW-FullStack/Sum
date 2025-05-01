package Cash;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayMethod {
	Random rd = new Random();
	Scanner sc = new Scanner(System.in);

	private int fprice = 0; // 최종 금액
	private int refund = 0; // 환불금
	private int dayCount = 0; // 성공한 날짜
	CashDAO dao = new CashDAO();
    CashDTO dto = new CashDTO();
    RankDAO rdao = new RankDAO();
    RankDTO rdto = new RankDTO();

	public ArrayList<ObjectVO> object(int level) {
		// 개수 변경을 위해 ArrayList에 적음
		ArrayList<ObjectVO> objectList = new ArrayList<ObjectVO>();

		int radishNum = rd.nextInt(10) + 1; // 변경되는 무 값 5-> 무 개당 가격: 500,550,525
		int radishCount; // 무 개수
		int radishPrice = radishNum * 100; // 무 개당 가격

		int objectCount = 5;

		if (level == 2) { // Normal
			objectCount = 10;
			radishPrice = radishNum * 110;
		} else if (level == 3) { // Hard
			objectCount = 20;
			radishPrice = radishNum * 115;
		}

		radishCount = rd.nextInt(objectCount) + 1;

		// list에서 가격이랑 개수를 빼와서 main에서 곱해야함!!!!!!!!!!!!!!!
		objectList.add(new ObjectVO("가구", 10000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("생선", 5000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("곤충", 7000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("과일", 6000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("꽃", 3000, rd.nextInt(objectCount) + 1));
		objectList.add(new ObjectVO("무", radishPrice, radishCount));

		return objectList;
	}

	public int refundRandom(int level) {
		// 물품 환불 확률 (부분환불/전체환불/환불x)
		ArrayList<ObjectVO> objectList = new ArrayList<ObjectVO>();

		objectList = object(level);

		int refundNum = rd.nextInt(100) + 1; // 환불 받을 확률
		int index = rd.nextInt(objectList.size());

		if (refundNum <= 20) { // 부분 환불
			refund /= 2;
			if (refundNum <= 5) {
				refund = objectList.get(index).getObjectSel();
			}
		} else
			refund = 0;

		return refund;
		// 최종값에서 환불금 뺀 금액이 시재에 저장되어야하기에 환불금을 리턴 해줌
	}

	public void gameLevel(RankDTO dto, String userId, int level) {
		
		if (level == 1) {
			sellPrint(1);
		} else if (level == 2) {
			sellPrint(2);
		} else if (level == 3 && dto.getUserId().equals(userId)
				&& (dto.getGmLevel() == 2 && dto.getGmClear().equals("Y"))) {
			// 노말모드 클리어 여부 확인
			sellPrint(3);
		}
	}

	public void sellPrint(int level) {
		int radishPrice =0;
		int sum = 0;
		
		radishPrice = object(level).get(5).getObjectSel();
        System.out.println("     || 오늘의 무 값! >> " + radishPrice + " ||");
        System.out.println("===================================");
        try {
           Thread.sleep(800);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        
        
        for (int j = 0; j < 5; j++) { // 시재 영수증 출력
           
           if (rd.nextInt(100) + 1 <= 60) { // 
              int count = object(level).get(5).getObjectCount();
              int total = radishPrice*count; 
              
//              System.out.println(play.object(1).get(5).getObject() + " " + count + "개를 팔았다! 총 " + total +"벨을 벌었다!");
              // 개수 자리수 2칸 확보 (오른쪽 정렬), 가격 자리수 6칸 확보
              System.out.printf("%-2s %2d개를 팔았다! 총 %6d벨을 벌었다!%n", object(level).get(5).getObject(), count, total);
              sum += total;
              try {
                 Thread.sleep(1000);
              } catch (InterruptedException e) {
                 e.printStackTrace();
              }

           } else {
              int rdNum = rd.nextInt(4);
              int price = object(level).get(rdNum).getObjectSel();
              int count = object(level).get(rdNum).getObjectCount();
              int total = price*count; 
              
//              System.out.println(play.object(1).get(rdNum).getObject() + " " + count + "개를 팔았다! 총 " + total +"벨을 벌었다!");
              System.out.printf("%-2s %2d개를 팔았다! 총 %6d벨을 벌었다!%n", object(level).get(rdNum).getObject(), count, total);
              sum += total;
              
              try {
                 Thread.sleep(1000);
              } catch (InterruptedException e) {
                 e.printStackTrace();
              }
           }
        }
        System.out.println("================================");
        System.out.println(sum);
        System.out.print("오늘의 매출액은? >> ");
        int value = sc.nextInt();
        System.out.println("================================");

        if (value == sum) {
           dayCount++;
           if (dayCount == 7) {
              System.out.println("\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀=================⠀⠀⠀⠀⠀⠀⠀⠀      \r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀          \r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀ ⠀⠀⠀⠀⠀⠀⢀⢀⢀⡀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢠⣠⡠⡀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀고생했다구리!⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⡀⣦⢾⢝⢟⠾⣝⢆⠀⠀⠀⠀⠀⠀⠀⢄⢏⢟⢎⢟⢽⢾⣔⠀⠀⠀⠀⠀⠀  다음에 또 와라구리! ⠀⠀⠀⠀⠀⠀⠀⠀⠀      ⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⠰⣽⡳⡑⠅⠕⠕⢜⢐⠄⢅⢅⢅⢅⢅⢑⢌⢊⢊⠢⠡⡑⢕⢯⣗⠀⠀⠀⠀⠀ 이렇게 성실한 알바생은 ⠀⠀⠀⠀⠀⠀        \r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⠘⡜⡜⡜⡌⡌⡎⡆⣆⣇⣇⢧⣣⢇⣧⣣⢕⣕⢔⢅⠕⢌⠪⡪⡪⠀⠀⠀⠀⠀⠀언제든지 환영이다구리~⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   \r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⢈⢎⢎⣎⡮⡾⣽⣺⢞⡾⣽⣺⢽⣺⣺⢽⣺⢽⣺⢜⡔⡅⡑⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀                \r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀ ⠀⠀⠀⠠⡸⡸⡼⡾⡽⡯⣗⣯⢯⡯⣗⡯⣟⡾⡽⣽⣺⢽⣺⢽⡺⣜⢔⢐⠀⠀⠀⠀⠀=================⠀⠀⠀⠀⠀⠀  ⠀   ⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀⢰⢱⢽⢯⢏⢇⣇⢇⢏⣯⢯⡯⣯⢷⣻⣽⡳⡣⣣⣢⣑⢝⡾⣸⢐⢅⠀⠀⠀⠀⠀                   ⠀⠀⠀⠀⠀⠀⠀⠀⠀  \r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⣄⡦⣢⣄⠀⠀⠀⠀⠀⠀⠀ ⡰⣼⡺⡶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⡪⣪⢿⡽⡮⣛⢎⣏⢗⣽⢯⡯⣯⢯⡷⣳⢯⡺⣹⣸⡹⣪⣞⣗⢕⠔⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣰⢴⡵⣆⠄⠀⠀⠀⠀⠀⠀ ⢠⣢⢶⣔⣄⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⣰⢽⠣⠫⡑⢕⠕⢄⢐⢐⢐⠐⢄⠣⡑⠌⠌⢝⢾⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⡇⣗⢿⡽⣯⢿⡽⣞⣯⢯⢫⠫⡪⠫⡚⢝⢯⣻⣳⣗⣟⣗⣗⣗⢕⢕⠅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡝⡪⡑⢌⢪⠪⡐⡐⡐⠄⠄⠄⢇⠣⠡⡑⢝⣷⡀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⢺⢱⠡⡑⡌⣆⣕⢵⢵⣕⢧⣳⢵⡱⣌⢌⢌⢢⠣⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢕⢵⢹⢝⡯⣟⢽⢝⢎⢎⢦⣳⣞⡷⣌⢆⢕⢜⢜⢞⢗⢯⢺⢸⢸⠰⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⡱⡐⡌⡆⣕⡵⡼⣜⣮⡺⡼⣜⣔⢅⠕⢌⢢⢳⠁⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⢑⢕⡵⡝⡞⡾⣝⡷⡽⣽⣺⢽⡺⡑⡫⡲⡄⢅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢎⢗⢵⡱⡕⡵⡱⡕⣝⢽⢾⣳⡿⣽⣣⢣⢣⢣⢣⢣⢣⢣⢣⠣⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡱⣱⢵⢫⢳⢫⡯⣗⣗⡯⣟⣞⢮⠳⢝⢆⢆⠁⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⢐⢜⡾⡹⡸⡸⡨⣳⢯⡯⣗⡯⣗⢕⢕⢔⠌⢞⣆⢂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠜⢮⢳⢝⢮⡺⡪⣏⢯⡳⡯⡳⣕⢇⢧⢣⡳⡱⡣⠃⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢝⡞⡕⡕⡕⡕⣯⣟⡾⣝⡷⣝⢜⢜⢔⠹⡸⡌⠄⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⡸⣪⣗⢵⢫⢺⡪⣺⢯⡯⣷⣻⡽⣜⠭⡍⡏⡮⣺⢢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢌⢊⠫⣪⢳⢝⢮⢫⢎⠇⡋⡢⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⣗⡯⡧⡏⡏⡗⣕⣷⣳⣟⣗⣯⣗⡕⡏⡳⣱⢹⡪⡢⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠠⡹⣵⢯⡪⡪⡢⣝⡽⡯⢯⢳⢳⢯⢷⡱⡱⡱⣹⢮⡣⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠨⡘⢜⠠⢑⠘⢎⢇⢇⢇⠇⠅⡂⠌⠌⠌⠄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣜⣯⣗⢵⢱⡱⣽⣞⢗⢗⢟⢾⣺⣎⢎⢎⢎⢮⡳⡅⠅⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠠⡹⣺⡽⡾⣵⣻⢞⢝⢜⡴⣦⡢⡑⢝⢽⢽⣺⣝⡗⡇⡂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠨⠨⡐⢌⠢⡈⡂⠅⠌⠪⡪⠊⠌⡐⠠⠡⠡⠡⢁⠢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⢱⢷⢯⡷⣗⣟⢇⢇⡵⣵⢥⠡⠣⡫⣗⡷⡽⣽⢺⠨⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠱⡱⡹⡫⡳⡹⡸⡸⡵⣻⣗⢿⡸⡰⡱⡹⡸⡸⡸⡘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡸⡰⡈⠢⡑⡐⠄⠅⢅⢑⠠⠡⡑⠌⠌⢌⠌⠌⡆⡇⡅⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢇⢏⢏⢏⢇⢇⢇⢗⡿⡽⡯⡏⡎⡎⡎⡝⡝⡪⡪⡊⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠑⠱⠱⢕⢕⢕⢵⢹⡪⡺⡱⡕⣕⢕⢕⠕⠕⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⡣⡣⡣⠣⡑⡐⠌⠌⢌⢂⠢⠣⡑⠨⠨⢈⢪⢪⢂⢸⢸⢸⢰⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠑⠇⠗⢵⢹⢜⢮⢫⡫⣪⢪⢪⢪⠪⠪⠊⠂⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⢨⢪⢣⢣⢫⢪⢪⢢⢣⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢽⢮⡪⡊⡂⠢⠨⠨⡈⡂⠢⠨⠐⠌⠌⢌⠢⡑⢕⢐⠠⠑⢗⣷⣳⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢠⢸⢸⢱⢹⢸⢸⢸⢸⢨⠢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡌⡎⡎⡎⠪⠨⢈⠂⠅⡑⢕⢕⢌⢂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⡝⠃⠀⠢⠨⠨⡈⡂⠢⠨⠨⠸⡨⠨⡈⠢⡑⠨⢐⢐⠨⡀⠀⠑⠯⠓⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⡆⡇⡇⡇⠣⠡⠡⠡⠡⡑⢕⢕⢕⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⢠⢱⢱⢱⠱⠨⠨⠨⢐⠨⢐⢐⠠⡑⡕⣕⢵⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠨⠨⡈⡂⡢⠨⠨⠨⠨⢐⠠⠑⠌⡂⠌⢌⢂⢂⢂⢂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢢⡣⡣⡣⡣⠨⠨⠨⠨⠨⢐⢈⠢⡑⡕⡕⣔⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⣯⢞⠘⣞⡼⣬⢬⢬⡰⣌⢦⣢⢶⢵⠅⠘⠝⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢑⢐⠐⠌⠌⠌⠌⢌⠢⠣⠡⡑⢄⢑⢐⢐⢐⢐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⢟⠎⠁⣟⡮⣮⢬⡬⣌⢬⡰⣔⢴⣣⠃⠻⢮⠇⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡳⣽⡺⡽⣝⢾⢵⢻⣪⢯⡳⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢕⢥⢡⠡⠡⡑⠄⠅⢅⠑⢌⢂⢂⢂⢂⢂⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⣗⡯⣗⡯⣞⢷⢝⢾⢝⡵⣳⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡪⡷⡽⣝⢎⢎⢎⢗⡽⣕⢯⡪⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡸⡸⡸⡱⡸⡸⡸⢰⢱⢰⢰⠱⡑⡑⠄⠅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⣗⡯⣗⡯⡪⡪⡣⢯⡳⣝⢮⡂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠽⣝⣞⢼⠸⡪⢗⡽⡎⠓⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠡⡑⢔⠐⠌⢌⢊⠊⠈⢎⠢⠢⡑⡐⠌⠌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠓⢯⢷⡝⡼⡸⠽⣕⢯⠺⠕⠅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠊⠁⠀⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠁⠑⠐⠀⠀⠀ ⠀⠁⠁⠂⠈⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠋⠃⠀⠀⠀⠋⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
                     + "");
              System.exit(0);
           }
           
        } else if (value != sum) {
           System.out.println("\r\n" + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⣿⣿⡿⢛⠉⠂⠄⠂⠐⠐⠠⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠫⠁⠄⠂⠁⠠⠀⠍⠛⠿⣿⣿⣿⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⡿⠑⠀⠄⡨⡐⡔⡱⡨⡢⡂⡡⠣⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢣⢑⠨⡐⡌⣎⢢⢢⢂⠐⠠⠈⠻⣿⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⡏⠄⠂⡁⡎⣖⢵⢝⢮⡫⡎⣎⢔⣕⣽⢿⣻⡿⣟⣿⢿⢿⢿⢿⢿⢿⢿⣻⣬⣢⡱⡱⡝⣎⢗⡵⣣⢧⢡⠈⡀⢙⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⢅⢐⠠⡪⡺⣕⢯⣫⢷⡽⡾⣯⢿⡽⣾⣻⡽⣽⢯⢯⣟⣯⢿⡻⡯⣟⡿⣽⣞⣷⣻⢯⣟⣮⣳⢝⡞⡮⡪⡂⠄⢂⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣷⡠⠣⡪⡺⣜⣷⣻⡽⣯⣻⢽⢽⢽⣺⢵⢯⢗⡯⣟⣞⢾⢽⢽⣝⣗⡯⣗⣗⣗⡯⣟⡽⣞⡯⣟⣮⡫⡪⡪⢊⣴⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⣿⣮⣜⡾⣽⣺⡺⣝⣞⢮⢯⢯⡳⡽⢕⠯⡫⡛⠞⢞⠏⡏⢗⠳⢕⠯⡳⡳⡳⣝⢗⡯⣗⢯⣻⣺⣻⢮⣴⣿⣿⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⣿⣟⢗⡽⡺⣜⡝⡞⡜⠝⡘⡐⡁⡂⠅⡂⠢⠨⠨⡈⠢⠨⢐⠨⢐⠐⠄⠅⢅⠑⠍⠎⢗⢯⡺⣺⢺⢽⣞⣿⣿⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⣟⢮⢳⡹⡺⠘⢌⠐⠄⠅⡐⠠⠂⠄⠅⢂⠡⠁⠅⢂⠡⢁⠂⠌⡐⠨⠈⠌⠠⠊⠨⠈⠔⡐⠩⡪⣏⢗⢗⡽⣿⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⡟⣜⢎⢇⠇⢊⠈⠄⠌⠄⡁⢂⠡⠈⠄⠅⠂⠌⠨⠐⡐⠈⠄⠌⠂⡂⠡⢁⠁⠅⠨⢈⠨⠐⠠⢁⢂⠊⡇⡯⡺⣝⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⡟⡎⡎⡎⡊⢐⠐⡈⣐⢴⣱⢲⡢⣄⠡⠈⠄⠡⠨⠠⠁⠄⠡⠈⠄⠡⠐⡈⠄⠨⢀⡥⡖⣖⢵⣨⠀⡂⠌⠌⡪⡫⡪⣞⣿⣿⣿\r\n"
                 + "⣿⣿⣿⡹⡸⡸⢐⠐⡀⠂⣴⢷⠳⡕⡯⡎⡗⡥⠨⠠⢑⠨⠠⠡⠡⠡⠡⠡⡁⡂⡂⠌⢄⢧⢳⡹⣪⠳⢵⣷⡠⠈⠔⢐⢱⢱⢱⢽⣿⣿\r\n"
                 + "⣿⣿⣗⢕⢕⡑⠄⢂⠀⡡⣿⡋⡢⠡⡃⡇⡇⡏⡌⢌⢂⠪⡈⡪⠨⡊⢌⢊⠔⡐⠌⢌⢢⢣⠣⡣⢃⠅⠅⡿⣇⢈⠐⢐⠨⡪⡪⡪⣿⣿\r\n"
                 + "⣿⣿⡇⡇⡲⠨⠐⢀⠐⢘⣟⡎⡐⡑⡌⡢⣪⢸⠠⡑⢔⢑⢌⠢⡃⢎⢢⢑⢌⢌⢊⠢⡊⣆⡇⡊⢆⠕⡡⣻⣝⠀⡈⠄⠂⡕⢜⠜⣿⣿\r\n"
                 + "⣿⣿⣇⠣⡊⡊⠄⠂⠐⡀⢟⣧⡥⡑⡌⣦⢿⡕⢕⠌⡆⡣⡊⡪⡘⡌⢆⠕⡌⢆⢣⠱⢸⡺⣧⢕⡡⣊⣴⣻⠂⢂⠀⢂⠁⢎⠜⡌⣿⣿\r\n"
                 + "⣿⣿⣧⠣⡑⢔⠀⡁⠂⡀⠌⠓⡯⣯⢯⡗⡏⡊⡢⢱⢘⢔⢥⢱⡸⣨⡢⣣⢱⢡⢑⢅⢃⠝⠵⣯⣻⣺⠺⠑⠈⢀⠐⠠⢈⠆⡣⣱⣿⣿\r\n"
                 + "⣿⣿⣿⣕⢌⠢⠡⡀⠄⠀⠄⠂⠂⡂⠅⡂⠪⡐⡜⡼⣜⢞⢮⡳⣝⡞⣞⢮⡳⣕⢧⡣⡢⡡⢑⢐⠐⡐⠈⠠⠈⢀⠐⡨⢐⢑⢌⣾⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣆⢕⠡⡂⡢⢁⠄⡐⡀⡂⢌⢂⢇⢇⢏⢞⢮⢫⡳⡽⡵⡻⡮⣗⢽⢺⢕⡗⡵⡱⡡⢂⠢⢐⢈⠠⢈⠄⡢⢊⠔⣅⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⣶⡑⢔⢐⠡⢂⢂⠢⡈⡢⢑⢌⠪⡪⢪⢊⠢⠑⡁⠌⠀⠌⠊⡊⠎⢎⠞⡜⢌⢊⠢⡑⡐⠔⡨⢂⢑⠔⡡⣱⣾⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⣿⣿⣷⣌⢌⠢⡡⢊⢐⠄⢅⠢⢑⠌⡂⠂⠄⢁⠠⠀⡂⠄⡁⢐⠨⠐⡑⢌⠪⡐⢅⠢⡊⢌⠢⡑⡔⣕⣿⣿⣿⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣦⣑⠔⢅⠕⢌⠢⡑⠌⢌⢐⢀⠂⡂⠄⢂⢐⠠⢂⢑⢌⠢⡑⢌⢢⢑⢌⣦⣵⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
                 + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣷⣵⣵⣜⣸⡐⡅⢆⢅⠢⡊⢔⠰⡨⡢⣱⣰⣱⣵⣵⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + "");
           System.out.println("================================");
           System.out.println();
           System.out.println(" " + dto.getUserName() + "! 시재 점검이 틀렸잖아!!!!!!");
           System.out.println();
           System.out.println("================================");
           System.out.println();
           System.out.println("================================");
           System.out.println();
           System.out.println("        너굴 상점 폐업.....");
           System.out.println();
           System.out.println("================================");

           System.exit(0);
        } 
	}
	
}
