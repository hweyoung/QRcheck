package com.codeus.user;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "hongpark");
        return "hello";
    }


    @GetMapping("/list") //추가함
    public String getUserList(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        return "admin";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "loginPage";
    }
//    @PostMapping("/user")
//    public String loginUser(@RequestBody User user,HttpServletRequest request, HttpSession session, Model model) throws Throwable {
//        System.out.println(user);
//        User response = userService.loginUser(user);
//        String qr = makeqr(request,session,user.getUUID());
//        model.addAttribute("uuid", response.getUUID());
//        model.addAttribute("name",response.getName());
//        model.addAttribute("qr",qr);
//        return "user";
//    }

    @GetMapping("/user/{uuid}/{pwd}")
    public String loginUser(@PathVariable String uuid, @PathVariable String pwd, HttpServletRequest request, HttpSession session, Model model) throws Throwable {
        User user = new User();
        user.setPwd(pwd);
        user.setUUID(uuid);
        System.out.println(user);
        User response = userService.loginUser(user);
        String qr = makeqr(request,session,user.getUUID());
        model.addAttribute("uuid", response.getUUID());
        model.addAttribute("name",response.getName());
        model.addAttribute("qr",qr);
        return "user";
    }

    public String makeqr(HttpServletRequest request, HttpSession session,@PathVariable String storeName) throws WriterException, IOException {

        String root = request.getSession().getServletContext().getRealPath("resources"); //현재 서비스가 돌아가고 있는 서블릿 경로의 resources 폴더 찾기

        String savePath = root + "/qrCodes/"; // 파일 경로

        //파일 경로가 없으면 생성하기
        File file = new File(savePath);
        if(!file.exists()) {
            file.mkdirs();
        }

        // 링크로 할 URL주소
        String url = storeName;

        // 링크 생성값
        String codeurl = new String(url.getBytes("UTF-8"), "ISO-8859-1");

        // QRCode 색상값
        int qrcodeColor =   0xFF2e4e96;
        // QRCode 배경색상값
        int backgroundColor = 0xFFFFFFFF;

        //QRCode 생성
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE,200, 200);    // 200,200은 width, height

        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);

        //파일 이름에 저장한 날짜를 포함해주기 위해 date생성
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName=sdf.format(new Date()) +storeName;

        //파일 경로, 파일 이름 , 파일 확장자에 맡는 파일 생성
        File temp =  new File(savePath+fileName+".png");

        // ImageIO를 사용하여 파일쓰기
        ImageIO.write(bufferedImage, "png",temp);

        //리턴은 사용자가 원하는 값을 리턴한다.
        //작성자는 QRCode 파일의 이름을 넘겨주고 싶었음.
        return fileName+".png";
    }

}
