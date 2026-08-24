package com.expensetracker.backend;

import com.expensetracker.backend.model.User;
import com.expensetracker.backend.model.Expense;
import com.expensetracker.backend.service.UserService;
import com.expensetracker.backend.service.ExpenseService;
import com.expensetracker.backend.model.Income;
import com.expensetracker.backend.service.IncomeService;
import com.expensetracker.backend.model.Budget;
import com.expensetracker.backend.service.BudgetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        ExpenseService expenseService = new ExpenseService();
        IncomeService incomeService = new IncomeService();
        BudgetService budgetService = new BudgetService();

        System.out.println("=================================");
        System.out.println(" Expense Tracker Backend ");
        System.out.println("=================================");

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Add Expense");
        System.out.println("4. View Expenses");
        System.out.println("5. Update Expenses");
        System.out.println("6. Delete Expenses");
        System.out.println("7. Add Income");
        System.out.println("8. View Income");
        System.out.println("9. Update Income");
        System.out.println("10. Delete Income");
        System.out.println("11. Add Budget");
        System.out.println("12. View Budgets");
        System.out.println("13.Update Budget");
        System.out.println("14.Delete Budget");
        System.out.println("13. Exit");

        System.out.print("Choose Option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1: {

                User user = new User();

                System.out.print("Full Name: ");
                user.setFullName(scanner.nextLine());

                System.out.print("Email: ");
                user.setEmail(scanner.nextLine());

                System.out.print("Password: ");
                user.setPassword(scanner.nextLine());

                boolean registered = userService.registerUser(user);

                if (registered)
                    System.out.println("Registration Successful");
                else
                    System.out.println("Registration Failed");

                break;
            }

            case 2: {

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                boolean loggedIn = userService.loginUser(email, password);

                if (loggedIn)
                    System.out.println("Login Successful");
                else
                    System.out.println("Login Failed");

                break;
            }

            case 3: {

                Expense expense = new Expense();

                System.out.print("User ID: ");
                expense.setUserId(scanner.nextInt());

                System.out.print("Amount: ");
                expense.setAmount(scanner.nextBigDecimal());

                scanner.nextLine();

                System.out.print("Category: ");
                expense.setCategory(scanner.nextLine());

                System.out.print("Description: ");
                expense.setDescription(scanner.nextLine());

                System.out.print("Expense Date (yyyy-MM-dd): ");
                expense.setExpenseDate(LocalDate.parse(scanner.nextLine()));

                boolean expenseSaved = expenseService.addExpense(expense);

                if (expenseSaved)
                    System.out.println("Expense Added Successfully.");
                else
                    System.out.println("Failed to Add Expense.");

                break;
            }

            case 4: {

                System.out.print("Enter User ID: ");
                int userId = scanner.nextInt();

                List<Expense> expenses = expenseService.getExpensesByUser(userId);

                if (expenses.isEmpty()) {

                    System.out.println("No expenses found.");

                } else {

                    System.out.println("\n===============================");
                    System.out.println("Your Expenses");
                    System.out.println("===============================");

                    for (Expense expense : expenses) {

                        System.out.println("----------------------------");
                        System.out.println("Amount      : " + expense.getAmount());
                        System.out.println("Category    : " + expense.getCategory());
                        System.out.println("Description : " + expense.getDescription());
                        System.out.println("Date        : " + expense.getExpenseDate());
                    }
                }

                break;
            }

            case 5: {
                Expense expense = new Expense();
                System.out.println("Expense Id:");
                expense.setId(scanner.nextInt());
                System.out.println("Amount");
                expense.setAmount(scanner.nextBigDecimal());
                scanner.nextInt();
                System.out.println("Category");
                expense.setCategory(scanner.nextLine());
                System.out.println("Description");
                expense.setDescription(scanner.nextLine());
                System.out.println("Expense Date(yyyy-mm-dd)");
                expense.setExpenseDate(LocalDate.parse(scanner.nextLine()));

                boolean updated = expenseService.updateExpense(expense);
                if(updated)
                    System.out.println("Updated");
                else
                    System.out.println("Fail");

                break;
            }

            case 6:{
                System.out.println("Enter Expense Id");
                int expenseId = scanner.nextInt();
                boolean deleted = expenseService.deleteExpense(expenseId);
                if(deleted)
                    System.out.println("Deleted");
                else
                    System.out.println("Not Found");
                break;
            }

            case 7: {
                Income income = new Income();
                System.out.println("User ID:");
                income.setUserId(scanner.nextInt());
                System.out.println("Amount:");
                income.setAmount(scanner.nextBigDecimal());
                scanner.nextLine();
                System.out.print("Source: ");
                income.setSource(scanner.nextLine());
                System.out.print("Income Date: ");
                income.setIncomeDate(LocalDate.parse(scanner.nextLine()));
                boolean saved = incomeService.addIncome(income);
                if(saved)
                    System.out.print("Added Sucessfully");
                else
                    System.out.print("Failed ");
                break;

            }

            case 8: {

                System.out.print("Enter User ID: ");
                int userId = scanner.nextInt();

                List<Income> incomes = incomeService.getIncomeByUser(userId);

                if (incomes.isEmpty()) {

                    System.out.println("No income found.");

                } else {

                    System.out.println("\n===============================");
                    System.out.println("Income History");
                    System.out.println("===============================");

                    for (Income income : incomes) {

                        System.out.println("----------------------------");
                        System.out.println("ID          : " + income.getId());
                        System.out.println("Amount      : " + income.getAmount());
                        System.out.println("Source      : " + income.getSource());
                        System.out.println("Date        : " + income.getIncomeDate());
                    }
                }

                break;
            }

            case 9: {

                Income income = new Income();

                System.out.print("Income ID: ");
                income.setId(scanner.nextInt());

                System.out.print("Amount: ");
                income.setAmount(scanner.nextBigDecimal());

                scanner.nextLine();

                System.out.print("Source: ");
                income.setSource(scanner.nextLine());

                System.out.print("Income Date (yyyy-MM-dd): ");
                income.setIncomeDate(LocalDate.parse(scanner.nextLine()));

                boolean updated = incomeService.updateIncome(income);

                if (updated)
                    System.out.println("Income Updated Successfully.");
                else
                    System.out.println("Failed to Update Income.");

                break;
            }

            case 10: {

                System.out.print("Income ID: ");

                int incomeId = scanner.nextInt();

                boolean deleted = incomeService.deleteIncome(incomeId);

                if (deleted)
                    System.out.println("Income Deleted Successfully.");
                else
                    System.out.println("Failed to Delete Income.");

                break;
            }

            case 11: {

                Budget budget = new Budget();

                System.out.print("User ID: ");
                budget.setUserId(scanner.nextInt());

                scanner.nextLine();

                System.out.print("Category: ");
                budget.setCategory(scanner.nextLine());

                System.out.print("Budget Amount: ");
                budget.setAmount(scanner.nextBigDecimal());

                System.out.print("Month (1-12): ");
                budget.setMonth(scanner.nextInt());

                System.out.print("Year: ");
                budget.setYear(scanner.nextInt());

                boolean saved = budgetService.addBudget(budget);

                if (saved)
                    System.out.println("Budget Added Successfully.");
                else
                    System.out.println("Failed to Add Budget.");

                break;
            }

            case 12: {

                System.out.print("Enter User ID: ");

                int userId = scanner.nextInt();

                List<Budget> budgets =
                        budgetService.getBudgetsByUser(userId);

                if (budgets.isEmpty()) {

                    System.out.println("No budgets found.");

                } else {

                    System.out.println("\n===============================");
                    System.out.println("Your Budgets");
                    System.out.println("===============================");

                    for (Budget budget : budgets) {

                        System.out.println("----------------------------");
                        System.out.println("ID       : " + budget.getId());
                        System.out.println("Category : " + budget.getCategory());
                        System.out.println("Amount   : " + budget.getAmount());
                        System.out.println("Month    : " + budget.getMonth());
                        System.out.println("Year     : " + budget.getYear());
                    }
                }

                break;
            }
            case 13: {

                Budget budget = new Budget();

                System.out.print("Budget ID: ");
                budget.setId(scanner.nextInt());

                scanner.nextLine();

                System.out.print("Category: ");
                budget.setCategory(scanner.nextLine());

                System.out.print("Budget Amount: ");
                budget.setAmount(scanner.nextBigDecimal());

                System.out.print("Month (1-12): ");
                budget.setMonth(scanner.nextInt());

                System.out.print("Year: ");
                budget.setYear(scanner.nextInt());

                boolean updated = budgetService.updateBudget(budget);

                if (updated)
                    System.out.println("Budget Updated Successfully.");
                else
                    System.out.println("Failed to Update Budget.");

                break;
            }
            case 14: {

                System.out.print("Budget ID: ");

                int budgetId = scanner.nextInt();

                boolean deleted = budgetService.deleteBudget(budgetId);

                if (deleted)
                    System.out.println("Budget Deleted Successfully.");
                else
                    System.out.println("Failed to Delete Budget.");

                break;
            }

            case 15: {

                System.out.println("Thank you for using Expense Tracker.");
                break;
            }

            default: {
                System.out.println("Invalid Choice");
            }
        }

        scanner.close();
    }
}