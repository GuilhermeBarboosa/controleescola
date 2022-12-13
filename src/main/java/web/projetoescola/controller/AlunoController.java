package web.projetoescola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import web.projetoescola.service.RelatorioService;


@Controller
@RequestMapping("/aluno")
public class AlunoController {

	
	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
        ModelAndView mv = new ModelAndView("mostrarmensagem");
        mv.addObject("mensagem", "Apenas o admin e secretaria podem acessar. Estrobilobaldo, Ana, João e Sheila podem ver isso aqui.");
        return mv;
	}
	
	@GetMapping("/buscar")
	public ModelAndView buscar() {
		ModelAndView mv = new ModelAndView("mostrarmensagem");
		mv.addObject("mensagem", "Apenas o admin e secretaria podem acessar. Estrobilobaldo, Ana, João e Sheila podem ver isso aqui.");
        return mv;
	}
	
    @GetMapping("/remover")
    public ModelAndView remover() {
    	ModelAndView mv = new ModelAndView("mostrarmensagem");
    	mv.addObject("mensagem", "Apenas o admin e secretaria podem acessar. Estrobilobaldo, Ana, João e Sheila podem ver isso aqui.");
        return mv;
    }
    
    @GetMapping("/alterar")
    public ModelAndView alterar() {
    	ModelAndView mv = new ModelAndView("mostrarmensagem");    	
    	mv.addObject("mensagem", "Apenas o admin e secretaria podem acessar. Estrobilobaldo, Ana, João e Sheila podem ver isso aqui.");
        return mv;
    }
    
    @GetMapping("/notas")
    public ModelAndView notas() {
    	ModelAndView mv = new ModelAndView("mostrarmensagem");
        mv.addObject("mensagem", "Apenas o admin e professor podem acessar. Estrobilobaldo, Ana, Jorge e Grosbilda podem ver isso aqui.");
        return mv;
    }
    
//    @GetMapping("/relatorio")
//    public ModelAndView relatorio() {
//    	ModelAndView mv = new ModelAndView("mostrarmensagem");
//        mv.addObject("mensagem", "Você deve alterar essa funcionalidade para que gere o relatório em PDF. Apenas o admin pode acessar. Só o Estrobilobaldo pode ver isso aqui.");
//        return mv;
//    }
	
	@GetMapping("/relatorio")
	public ResponseEntity<byte[]> gerarRelatorioDeNotas() {

		byte[] relatorio = relatorioService.gerarRelatorioDeNotas();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RelatorioDeNotas.pdf")
				.body(relatorio);
	}
}








