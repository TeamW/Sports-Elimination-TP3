all: finalReport.pdf

finalReport.pdf: finalReport.tex finalReport.bib $(wildcard includes/*.tex)
	pdflatex finalReport
	bibtex finalReport
	pdflatex finalReport
	pdflatex finalReport
	rm -f *.log *.aux *.toc *.out
	rm -f *.bbl *.blg

clean:
	rm -f *.log *.aux *.toc *.out
	rm -f *.bbl *.blg
	rm -f finalReport.pdf