# SOA - DemoEJB

## Giới thiệu
Dự án này minh họa cách sử dụng EJB (Enterprise JavaBeans) với hai loại Bean: Stateful và Stateless, thông qua một ứng dụng web đơn giản thực hiện các phép tính toán học.

## Cấu trúc thư mục
- `src/main/java/Calculate/`: Chứa mã nguồn Java cho các Bean và Servlet.
- `src/main/webapp/`: Chứa tài nguyên web (JSP, HTML, META-INF, WEB-INF).
- `build/classes/`: Thư mục chứa các file đã biên dịch.
- `.settings/`, `.classpath`, `.project`: Các file cấu hình cho Eclipse.


## Hướng dẫn sử dụng trên Eclipse

### 1. Tải TomEE
- Kiểm tra phiên bản JDK trong máy của bạn
- Truy cập [https://tomee.apache.org/download.html](https://tomee.apache.org/download.html)
- Tải bản **Plume** và giải nén, ví dụ: `C:\apache-tomee-9.x.x-plume`

### 2. Thêm TomEE vào Eclipse

- Vào **Window > Preferences > Server > Runtime Environments > Add…**
- Chọn **Apache Tomcat v9.0** → trỏ đến thư mục TomEE vừa giải nén → **Finish**

### 3. Thêm server vào Eclipse

- Mở tab **Servers** → **New Server** → chọn **Tomcat v9.0 (TomEE)**
- Add project vào server → **Run on Server**

### 4. Import dự án mẫu vào Eclipse

- Mở Eclipse IDE.
- Chọn **File > Import > Existing Projects into Workspace**.
- Chọn thư mục gốc của dự án này.
- Nhấn **Finish** để import.

### 5. Build và chạy dự án

- Chuột phải vào project, chọn **Run As > Run on Server**.
- Chọn server TomEE đã cấu hình.
- Truy cập ứng dụng qua đường dẫn:  
  `http://localhost:8080/Demo/calculate`

### 6. Sử dụng ứng dụng

- Nhập hai số, chọn phép toán và loại Bean (Stateful hoặc Stateless).
- Xem kết quả và lịch sử tính toán (nếu chọn Stateful).

## Liên hệ
Nếu có vấn đề hoặc thắc mắc, vui lòng liên hệ giảng viên hoặc người hướng dẫn.

---
