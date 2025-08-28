# SOA - DemoEJB

## Giới thiệu
Dự án này minh họa cách sử dụng EJB (Enterprise JavaBeans) với hai loại Bean: Stateful và Stateless, thông qua một ứng dụng web đơn giản thực hiện các phép tính toán học.

## Cấu trúc thư mục
- `src/main/java/Calculate/`: Chứa mã nguồn Java cho các Bean và Servlet.
- `src/main/webapp/`: Chứa tài nguyên web (JSP, HTML, META-INF, WEB-INF).
- `build/classes/`: Thư mục chứa các file đã biên dịch.
- `.settings/`, `.classpath`, `.project`: Các file cấu hình cho Eclipse.

## Hướng dẫn sử dụng trên Eclipse

### 1. Import dự án vào Eclipse
- Mở Eclipse IDE.
- Chọn **File > Import > Existing Projects into Workspace**.
- Chọn thư mục gốc của dự án này.
- Nhấn **Finish** để import.

### 2. Cấu hình môi trường
- Đảm bảo bạn đã cài đặt **Apache TomEE 8.x** hoặc server hỗ trợ EJB.
- Kiểm tra JDK sử dụng là Java 17 (theo file `.classpath` và `.settings/org.eclipse.jdt.core.prefs`).

### 3. Build và chạy dự án
- Chuột phải vào project, chọn **Run As > Run on Server**.
- Chọn server TomEE đã cấu hình.
- Truy cập ứng dụng qua đường dẫn:  
  `http://localhost:8080/Demo/calculate`

### 4. Sử dụng ứng dụng
- Nhập hai số, chọn phép toán và loại Bean (Stateful hoặc Stateless).
- Xem kết quả và lịch sử tính toán (nếu chọn Stateful).

## Liên hệ
Nếu có vấn đề hoặc thắc mắc, vui lòng liên hệ giảng viên hoặc người hướng dẫn.

---