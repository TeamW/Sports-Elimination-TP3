all: example.pdf finalReport.pdf

example.pdf: example.tex example.bib
	pdflatex example
	bibtex example
	pdflatex example
	pdflatex example

finalReport.pdf: finalReport.tex finalReport.bib
	pdflatex finalReport
	bibtex finalReport
	pdflatex finalReport
	pdflatex finalReport

clean:
	rm -f *.log *.aux *.toc *.out
	rm -f *.bbl *.blg
	rm -f finalReport.pdf
	rm -f example.pdf
