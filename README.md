# Josip Finance CLI - User Guide

Welcome to **Josip Finance**, a command-line application designed to help users efficiently track and manage personal finances.

---

## ✨ Introduction

Josip Finance is a simple, keyboard-driven program that supports budgeting, transaction tracking, and spending analysis. It allows users to import CSV data and interact with it through a structured menu system.

### Main Features

* Budget overview and tracking
* Transaction list and category tagging
* Monthly/weekly/yearly spending statistics
* CSV file integration for budgets and transactions

---

## 🔍 Interface Overview

Josip Finance uses a text-based menu system:

* **Main Menu** (character-based selection):

  * `[ B ] Budget Overview`
  * `[ T ] View Transactions`
  * `[ S ] Analyze Spending`
  * `[ X ] Exit`

All inputs are done via keyboard. Results are printed to the terminal with visual cues (e.g. colors and banners).

---

## 🔧 Function Descriptions

### 1. Budget Overview

* Displays all budgets loaded from `budgets.csv`
* Shows each budget's name (category), period, limit, and start date
* List can be sorted and filtered by each data field

### 2. View Transactions

* Displays all transactions from `transactions.csv`
* Displays date, amount, category, narrative, and type (INCOMING/OUTGOING/PURCHASE)
* List can be sorted and filtered by each data field

### 3. Analyze Spending

* Analyzes information from the files
* Calculates combined spending, income and balance
* Check overspending on budget limited categories
* Lists 5 largest purchases

### 4. Add Transaction

* User enters:

  * Date (YYYY-MM-DD)
  * Type (INCOMING, OUTGOING, PURCHASE)
  * Narrative (e.g. "Mego Veikals")
  * Bank Reference (optional)
  * Amount (has to be negative for outgoing transactions)
  * Category (e.g. "Groceries")
* Saves new entry to `transactions.csv`

---

## ℹ️ Notes

* CSV files must be placed in the `resources/` directory
* Date inputs must follow the format `YYYY-MM-DD`

---

## 🚀 Getting Started

1. Go to the releases page
2. Download either clean version, or with demo data (both versions need to have the data files)
3. Extract the zip folder
3. Launch and follow menu options to use

---

Enjoy managing your finances with Josip Finance!
