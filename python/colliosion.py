import csv

with open("100_collision_data.csv", "w", newline="") as file:
    writer = csv.writer(file)

    writer.writerow(["key"])

    for i in range(100):
        writer.writerow([i * 7])

print("File 100_collision_data.csv berhasil dibuat")