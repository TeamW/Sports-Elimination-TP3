

finalReport.pdf: finalReport.tex finalReport.bib
	pdflatex finalReport
	bibtex finalReport
	pdflatex finalReport
	pdflatex finalReport

clean:
	rm -f *.log *.aux *.toc *.out
	rm -f *.bbl *.blg
	rm -f finalReport.pdf
	
