# SellingBreadApp
Application for selling bread have requirement
"
Viết 1 phần mềm Java cho mô phỏng cửa hàng bánh mì.Người dùng có thể mua bánh mì với nhiều loại thành phần khác nhau. Các thành phần để làm bánh mì: trứng chiên, Jambon, Chả lụa, chả bò, Nem, thịt, chà bông, cá hồi. Các loại thành phần sẽ kết hợp bất kỳ với nhau và một ổ bánh mì có thể chứa tối đa 8 thành phần.
1. Phần mềm này sẽ cho người dùng input vào loại bánh mì mà họ cần mua. Ví dụ người dùng cần mua bánh mì trứng chiên + chà bông, bánh mì Chả lụa + nem + chà bông, ...

2. Giá cả nguyên liệu: - Bánh mì không: 8k - Trứng chiên: 5k - Jambon: 7k - Chả lụa: 3k - Chả bò: 7k - Nem: 4k - Thịt: 5k - Chà bông: 3k - Cá hồi: 5k
Giá của ổ bánh mì sẽ là tổng giá của tất cả các thành phần.
Ví dụ:         
Bánh mì trứng + chà bông sẽ có giá là 8 (bánh mì không) + 5 (Trứng chiên) + 3 (Chà bông) = 16 k         
Bánh mì chả bò sẽ có giá là 8 (bánh mì không) + 7 (chả bò) = 15k

3. Phần mềm cho phép người dùng tra cứu lại danh sách bánh mì đã bán ra. Và danh sách này được sắp xếp giảm dần theo giá.
"
Extend
"
Yêu cầu thêm mới
1. Do tình hình cạnh tranh quá nhiều nên chuỗi bánh mỳ quyết định giảm giá cho mặt hàng bánh mi với giá cả nguyên liệu mới

2. Bên cạnh đó sẽ bổ sung thêm 1 mặt hàng sản phẩm là "hamburger". Riêng burger thì sẽ ko bán chung với cá hồi, chả lụa, chả bò, chà bông. Nhưng vẫn có thể dùng nhân trứng chiên, Jambon, Nem, Thịt. Burger diện tích nhỏ nên mỗi burger chỉ có thể có tối đa 5 nguyên liệu đi kèm

3. Bảng giá mới chỉ áp dụng cho nhưng đơn hàng mới, những đơn hàng cũ đã thanh toán theo giá cũng thì sẽ ko bị ảnh hưởng
"
