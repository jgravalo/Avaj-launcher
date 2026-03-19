all:
	javac @sources.txt

test:
	java avaj.Simulator scenario.txt

clean:
	rm avaj/*.class

fclean: clean
	rm simulation.txt

re:	fclean all

.PHONY: all test clean fclean re
