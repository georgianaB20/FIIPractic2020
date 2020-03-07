package fii.practic.yonder.demo.controller;

import fii.practic.yonder.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    //------------------------GET----------------------------
    //http://localhost:8081/test?name=fiipractic
    //http://localhost:8081/test/1 (PathVariable)
    //http://localhost:8081/test/1?name=fiipractic (RequestParam+PathVariable)
    //http://localhost:8081/doctors?pageNumber=0&pageSize=10 corect /0/10 not great,not terible,better nope-> cate 10 doctori pe pagina
    //in cazul de mai sus preferam
    /*@GetMapping//aici specific /{id} este un pathVariable pe care il primim prin request
    @RequestMapping(value="/doctors/{id}") //RequestParam(flags: required-nu crapa daca nu dau parametrul; by default required=true)
    public String getTestMessage(@RequestParam(value="name", required=false) String name,
                                 @PathVariable Long id){
        //return "Hello FII Practic 2020"; //(void)
        //return name; //RequestParam
        //return "Am primit id: "+id; //PathVariable
        return "Am primit id:"+id+"si name:"+name; //RequestParam+PathVariable
    }*/

    @Autowired //fara autowired nu se instantiaza ob de tipul TestService (adica testService=> testService.getTestMessage() =nullPtr)
    private TestService testService;
    //echivalent cu private TestService testService=new TestServiceImpl();  nu la fel de eficient
    // ---||--- private TestServiceImpl=new TestServiceImpl(); nu la fel de eficient

    @GetMapping
    @RequestMapping(value="/test")
    public String getTestMessage(){
        return testService.getTestMessage();
    }

    @GetMapping
    public String getNames(){
        return testService.getAll();
    }

    //------------------------POST-------------------
    @PostMapping
    public void save(@RequestBody String name){
        testService.save(name);
    }

    //---------------------PUT------------------
    @PutMapping(value="/{id}")
    //@RequestMapping(value="/{id}")
    public void update(@PathVariable Integer id,@RequestBody String newValue){
        testService.update(id,newValue);
    }

    //--------------------DELETE------------------
    //in cazul asta nu conteaza verbul-- are 2 path-uri si nu stie ce verb sa aleaga=>conflict
    @DeleteMapping
    @RequestMapping(value="/{id}")
    public void delete(@PathVariable Integer id){
        testService.delete(id);
    }



}
