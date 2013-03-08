all: finalReport.pdf clean

finalReport.pdf: finalReport.tex finalReport.bib $(wildcard includes/*.tex)
	pdflatex finalReport
	bibtex finalReport
	pdflatex finalReport
	pdflatex finalReport

clean:
	rm -f *.log *.aux *.toc *.out
	rm -f *.bbl *.blg
