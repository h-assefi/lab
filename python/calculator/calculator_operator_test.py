import unittest
from calc_operator import calculator_operator


class TestCalculatorOperator(unittest.TestCase):
    def test_add(self):
        self.assertEqual( calculator_operator.add(2,2),4)

    def test_minus(self):
        self.assertEqual( calculator_operator.minus(2,2),0)

    def test_multiply(self):
        self.assertEqual( calculator_operator.multiply(2,3),6)

    def test_division(self):
        self.assertEqual( calculator_operator.division(4,2),2)

    def test_division_by_zero(self):
        self.assertEqual( calculator_operator.division(4,0),0)

if(__name__ == "__main__"):
    unittest.main()
