package mk.ukim.finki.lab03.web.controller;

import mk.ukim.finki.lab03.model.Production;
import mk.ukim.finki.lab03.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/productions")
public class ProductionController {
    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public String getProductionsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }
        model.addAttribute("productions", productionService.findAll());
        return "listProduction";
    }


    @GetMapping("/add")
    public String saveProduction(Model model) {
        return "add-production";
    }

    @PostMapping("/save")
    public String save(@RequestParam String name,
                       @RequestParam String country,
                       @RequestParam String address
    ) {
        productionService.saveProduction(name, country, address);
        return "redirect:/productions";
    }

    @PostMapping("/edit/{prodId}")
    public String edit(@PathVariable Long prodId,
                       @RequestParam String name,
                       @RequestParam String country,
                       @RequestParam String address
    ) {

        productionService.editProduction(prodId, name, country, address);
        return "redirect:/productions";
    }

    @GetMapping("/edit-form/{prodId}")
    public String edit(@PathVariable Long prodId, Model model) {
        Optional<Production> production = productionService.findById(prodId);
        if (production.isPresent()) {
            model.addAttribute("production", production.get());
            return "add-production";
        }
        return "redirect:/productions?error=ProductionNotFound";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productionService.deleteById(id);
        return "redirect:/productions";
    }
}
