package oit.is.lec05.team6.kounyu.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import oit.is.lec05.team6.kounyu.model.Fruit;
import oit.is.lec05.team6.kounyu.model.FruitMapper;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sample5")
public class Sample51Controller {

  @Autowired
  FruitMapper fMapper;

  @GetMapping("/step1")
  public String Saple51() {
    return "sample51.html";
  }

  @GetMapping("step2")
  public String sample52(ModelMap model) {
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);
    return "sample51.html";
  }

  @GetMapping("step3")
  @Transactional
  public String sample53(@RequestParam Integer id, ModelMap model) {
    Fruit fruit3 = fMapper.selectById(id);
    model.addAttribute("fruit3", fruit3);
    fMapper.deleteById(id);
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);

    return "sample51.html";
  }

  @GetMapping("step4")
  @Transactional
  public String Sample34(@RequestParam Integer id, ModelMap model) {
    Fruit fruit4 = fMapper.selectById(id);
    model.addAttribute("fruit4", fruit4);
    ArrayList<Fruit> fruits2 = fMapper.selectAllFruit();
    model.addAttribute("fruits2", fruits2);

    return "sample51.html";
  }

  @PostMapping("step5")
  public String Sample35() {
    return "sample51.html";
  }
}
