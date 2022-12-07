package Entity;

import Utils.ScannerUtil;

public class MainUI {
    public static void run(){

        String select;
        while (true) {

            System.out.println("Your role is: ");
            System.out.println("1->" + Role.USER);
            System.out.println("2->" + Role.ADMIN);
            System.out.println("3->" + Role.MODERATOR);
            System.out.println("4->" + Role.WRITER);
            System.out.println("0. Exit");

            select = ScannerUtil.str.nextLine();

            switch (select) {
                case "0": return;
                case "1":
                    UserUI.page();
                    break;
                case "2":
                    AdminUI.page();
                    break;
                case "3":
                    ModeratorUI.page();
                    break;
                case "4":
                    WriterUI.page();
                    break;
                default:
                    Result.incorrect();
            }

        }
    }
}
