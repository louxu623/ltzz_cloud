package com.ltzz.modules.base.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luox
 * @description
 * @date 2020/9/28
 */
public class NaverController {

    /**
     * 生成小说文件夹
     *
     * @param novel
     * @return 成功生成返回文件夹名，已存在返回null
     */
    private static String genarateFolder(File novel) {
        if (!novel.isFile() || !novel.getAbsolutePath().endsWith(".txt")) {
            return null;
        }
        String novelName = novel.getAbsolutePath();
        String folderName = novelName.substring(0, novelName.indexOf(".txt"));
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdirs();
            return folderName;
        }
        return null;
    }

    /**
     * 输出html文件
     *
     * @param currentPageIndex
     * @throws Exception
     */
    private static void generateChapterHtmlFile(int currentPageIndex, String content, List<String> chapterList, String folderName) throws Exception {
        String pageContent = "<html>\n<head>\n"
                + "<meta http-equiv='content-type' content='text/html';charset='utf-8'>\n"
                + "<title>" + chapterList.get(currentPageIndex) + "</title>\n"
//                + "<script type=\"text/javascript\">\n"
//                + "window.onload = function(){"
//                + "//去掉默认的contextmenu事件，否则会和右键事件同时出现。\r\n"
//                + "document.oncontextmenu = function(e){"
//                + "e.preventDefault();"
//                + "};\n"
//                + " document.getElementById(\"body\").onmousedown = function(e){"
//                + " if(e.button ==2){"
//                + "window.open('" + chapterList.get(currentPageIndex == chapterList.size() - 1 ? chapterList.size() - 1 : currentPageIndex + 1) + ".html','_self')"
//                + "}else if(e.button ==0){"
//                + "window.open('" + chapterList.get(currentPageIndex == 0 ? 0 : currentPageIndex - 1) + ".html','_self')"
//                + "}else if(e.button ==1){"
//                + "window.open('章节目录.html','_self');}}}\n"
//                + "</script>\n"
                + "</head>\n<body bgcolor='#e6f3ff' id='body'>\n"
                + "<h1 align='center'>" + chapterList.get(currentPageIndex) + "</h1>\n"
                + "<div style='line-height:24px;font-size:24px;width: 50%;margin :auto'>" + content + "</div>\n"
                + "</br>\n"
                + "<table align='center'>\n"
                + "<tr><td><a href='" + chapterList.get(currentPageIndex == 0 ? 0 : currentPageIndex - 1) + ".html'>上一页</a></td></tr>"
                + "<tr><td><a href='章节目录.html'>目录</a></td></tr>"
                + "<tr><td><a href='" + chapterList.get(currentPageIndex == chapterList.size() - 1 ? chapterList.size() - 1 : currentPageIndex + 1) + ".html'>下一页</a></td></tr>"
                + "</table>\n"
                + "</body>\n"
                + "</html>\n";
        String filePath = folderName + "\\" + chapterList.get(currentPageIndex) + ".html";
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
        out.print(pageContent);
        out.flush();
        out.close();
    }

    /**
     * 获取章节列表
     *
     * @param novel
     * @throws Exception
     */
    private static List<String> getChapterList(File novel, String preStr) throws Exception {
        List<String> chapterList = new ArrayList<String>();
        FileInputStream fileInputStream = new FileInputStream(novel);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, getCharsetOfNovel(novel));
        BufferedReader novelbr = new BufferedReader(inputStreamReader);
        int currentIndex = 1;
        String line = novelbr.readLine();
        while (line != null) {
            if (line.indexOf(preStr) == 0 && line.indexOf("节") != -1) {
                chapterList.add("第" + currentIndex + "章" + line.substring(line.indexOf("节") + 1));
                currentIndex++;
            } else if (line.indexOf(preStr) == 0 && line.indexOf("章") != -1) {
                chapterList.add("第" + currentIndex + "章" + line.substring(line.indexOf("章") + 1));
                currentIndex++;
            }
            line = novelbr.readLine();
        }
        novelbr.close();
        fileInputStream.close();
        return chapterList;
    }

    private static void generateChapterMenuHtmlFile(String folderName, List<String> chapterList) throws Exception {
        String menuPath = folderName + "\\章节目录.html";
        StringBuilder pageContent = new StringBuilder();
        pageContent.append("<html><head>"
                + "<meta http-equiv='content-type' content='text/html';charset='utf-8'>"
                + "<title>" + folderName.substring(folderName.lastIndexOf("\\") + 1) + "章节目录</title>"
                + "<head>"
                + "<body bgcolor='#e6f3ff' id='body'>"
                + "<h1 align='center'>章节目录</h1><br>"
                + "<table style='margin :auto;'  cellpadding='10px' cellspacing='0' align='center' border='1'>");
        for (int i = 0; i < chapterList.size(); i++) {
            if (i == 0) {
                pageContent.append("<tr>");
            } else if (i % 5 == 0) {
                pageContent.append("<td><a href='" + chapterList.get(i) + ".html'>" + chapterList.get(i) + "</a></td>");
                pageContent.append("</tr>");
            }
            pageContent.append("<td><a href='" + chapterList.get(i) + ".html'>" + chapterList.get(i) + "</a></td>");
        }
        pageContent.append("</table></body></html>");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(menuPath)));
        out.print(pageContent.toString());
        out.flush();
        out.close();
    }

    /**
     * 判断TXT文件编码方式
     *
     * @return
     * @throws IOException
     */
    private static String getCharsetOfNovel(File novel) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(novel));
        byte[] head = new byte[3];
        bin.read(head, 0, head.length);
        String encoding = "gb2312";
        if (head[0] == -1 && head[1] == -2) {
            encoding = "UTF-16";
        }
        if (head[0] == -2 && head[1] == -1) {
            encoding = "Unicode";
        }
        if (head[0] == -17 && head[1] == -69 && head[2] == -65) {
            encoding = "UTF-8";
        }
        if (head[0] == -25 && head[1] == -84 && head[2] == -84) {
            encoding = "UTF-8";
        }
        return encoding;
    }

    public static void generate(File novel, String preStr) throws Exception {
        String folderName = genarateFolder(novel);
        if (folderName == null) {
            return;
        }
        List<String> chapterList = getChapterList(novel, preStr);
        generateChapterMenuHtmlFile(folderName, chapterList);
        FileInputStream fileInputStream = new FileInputStream(novel);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, getCharsetOfNovel(novel));
        BufferedReader novelbr = new BufferedReader(inputStreamReader);
        int currentPageIndex = -1;
        StringBuilder content = new StringBuilder();
        String line = novelbr.readLine();
        while (line != null) {
            if (line.indexOf(preStr) == 0 && (line.indexOf("节") != -1 || line.indexOf("章") != -1)) {
                if (currentPageIndex > -1) {
                    generateChapterHtmlFile(currentPageIndex, content.toString(), chapterList, folderName);
                    content.delete(0, content.length());
                }
                currentPageIndex++;
            } else if (currentPageIndex > -1) {
                content.append(line + "<br><br>");
            }
            line = novelbr.readLine();
        }
        novelbr.close();
        fileInputStream.close();
    }

    public static List<String> fileRead(File file) {
        List<String> res = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (!tempString.equals(tempString.trim())) {
                    System.err.println("有空格");
                }
                res.add(tempString.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
//        File file = new File("D:\\find.txt");
//        List<String> content = fileRead(file);
//        List<String> newCont = new ArrayList<>();
//        for (String tem : content) {
//            if (newCont.contains(tem)) {
//                System.err.println("存在相同数据：" + tem);
//            } else {
//                newCont.add(tem);
//            }
//        }

        /**  stock shouyi  */
        Double initMoney = 25000.00D;
        Double rate = 0.4D;
        Integer years = 30;
        String unit = "年";
        printLine(initMoney, rate, years, unit);

        /**  btc  */
//        Double initMoney = 100.00D;
//        Double rate = 0.05D;
//        Integer years = 500;
//        String unit = "天";
//        printLine(initMoney, rate, years, unit);
    }


    public static void printLine(Double initMoney, Double rate, Integer years, String unit) {
        Double yearMoney = 0.00D;
        StringBuilder sb;
        int startYear = 2021;
        int msgFlag = 0;
        for (int i = 0; i < years; i++) {
            sb = new StringBuilder();
            sb.append("【" + startYear + unit + "】 ==> 第【" + (i + 1) + unit + "】本金：" + initMoney.intValue() + "元，");
            yearMoney = initMoney * (1 + rate);
            sb.append("底金：" + yearMoney.intValue() + "元，");
            sb.append("当" + unit + "需收益：" + (yearMoney.intValue() - initMoney.intValue()) + "元。");
            if (msgFlag == 2 && yearMoney.intValue() > 10000000) {
                sb.append("恭喜您已实现真正的财务自由");
                msgFlag++;
            } else if (msgFlag == 1 && yearMoney.intValue() > 3000000) {
                sb.append("恭喜，基本财务自由已实现");
                msgFlag++;
            } else if (msgFlag == 0 && yearMoney.intValue() > 1000000) {
                sb.append("恭喜，超市自由了");
                msgFlag++;
            }
            System.err.println(sb.toString());
            initMoney = yearMoney;
            startYear++;
        }
    }
}
