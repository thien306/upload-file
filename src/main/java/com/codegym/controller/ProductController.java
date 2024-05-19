package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.IProductService;
import com.codegym.service.UploadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
@PropertySource("classpath:upload_file.properties")
public class ProductController {

    @Value("D:/codegym/modele 4-2/Data Binding & Form/upload-file/src/main/resources/upload/")
    private String upload;

    private final IProductService productService = new com.codegym.service.ProductService();

    @GetMapping("")
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/index";
    }

    @GetMapping("/create")
    public String showCreate(ModelMap model) {
        model.addAttribute("product", new Product());
        return "/create";
    }


    @PostMapping("/save")
    public String save(ProductForm productForm, RedirectAttributes redirectAttributes) throws IOException {
        MultipartFile file = productForm.getImage();
        String fileName = file.getOriginalFilename();

        // Tạo thư mục nếu nó không tồn tại
        File uploadDir = new File(upload);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Tạo thư mục con có tên "hình ảnh"
        File subDir = new File(uploadDir, "image");
        if (!subDir.exists()) {
            subDir.mkdirs();
        }

        // Lưu tập tin vào thư mục con
        FileCopyUtils.copy(file.getBytes(), new File(subDir, fileName));

        Product product = new Product();
        product.setName(productForm.getName());
        product.setInformation(productForm.getInformation());
        product.setImage("image/" + fileName);  // Lưu đường dẫn tương đối
        product.setId((int) (Math.random() * 10000));

        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "New product added successfully");
        return "redirect:/products";
    }


    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/update";
    }


    @PostMapping("/update")
    public String update(ProductForm productForm, RedirectAttributes redirectAttributes) throws IOException {
        Product product = productService.findById(productForm.getId());
        MultipartFile file = productForm.getImage();

        if (file != null && !file.isEmpty()) {
            // Tạo thư mục nếu nó không tồn tại
            File uploadDir = new File(upload);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Tạo thư mục con có tên "hình ảnh"
            File subDir = new File(uploadDir, "image");
            if (!subDir.exists()) {
                subDir.mkdirs();
            }

            // Lưu tập tin vào thư mục con
            String fileName = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(subDir, fileName));
            product.setImage("image/" + fileName);  // Lưu đường dẫn tương đối
        } else {
            // Giữ hình ảnh hiện có nếu không có hình ảnh mới được tải lên
            product.setImage(product.getImage());
        }

        product.setName(productForm.getName());
        product.setInformation(productForm.getInformation());
        productService.update(product.getId(), product);

        redirectAttributes.addFlashAttribute("success", "Product update successful");
        return "redirect:/products";
    }



    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes attributes) {
        productService.remove(product.getId());
        attributes.addAttribute("success", "Product deletion successful");
        return "redirect:/products";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/view";
    }
}
