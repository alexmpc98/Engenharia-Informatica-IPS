using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ESTeSoccer.Migrations
{
    public partial class Inicial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "League",
                columns: table => new
                {
                    LeagueId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Name = table.Column<string>(type: "nvarchar(20)", maxLength: 20, nullable: false),
                    Country = table.Column<string>(type: "nvarchar(20)", maxLength: 20, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_League", x => x.LeagueId);
                });

            migrationBuilder.CreateTable(
                name: "Team",
                columns: table => new
                {
                    TeamId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    LeagueId = table.Column<int>(type: "int", nullable: false),
                    Name = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Initials = table.Column<string>(type: "nvarchar(3)", maxLength: 3, nullable: false),
                    FoundingDate = table.Column<DateTime>(type: "datetime2", nullable: true),
                    NumberOfTitles = table.Column<int>(type: "int", nullable: false),
                    MainColor = table.Column<string>(type: "nvarchar(max)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Team", x => x.TeamId);
                    table.ForeignKey(
                        name: "FK_Team_League_LeagueId",
                        column: x => x.LeagueId,
                        principalTable: "League",
                        principalColumn: "LeagueId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Player",
                columns: table => new
                {
                    PlayerId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    TeamId = table.Column<int>(type: "int", nullable: true),
                    Name = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    DateOfBirth = table.Column<DateTime>(type: "datetime2", nullable: false),
                    MarketValue = table.Column<decimal>(type: "decimal(18,2)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Player", x => x.PlayerId);
                    table.ForeignKey(
                        name: "FK_Player_Team_TeamId",
                        column: x => x.TeamId,
                        principalTable: "Team",
                        principalColumn: "TeamId");
                });

            migrationBuilder.InsertData(
                table: "League",
                columns: new[] { "LeagueId", "Country", "Name" },
                values: new object[] { 1, "Portugal", "Liga Bwin" });

            migrationBuilder.InsertData(
                table: "League",
                columns: new[] { "LeagueId", "Country", "Name" },
                values: new object[] { 2, "Inglaterra", "Premier League" });

            migrationBuilder.InsertData(
                table: "Team",
                columns: new[] { "TeamId", "FoundingDate", "Initials", "LeagueId", "MainColor", "Name", "NumberOfTitles" },
                values: new object[,]
                {
                    { 1, null, "SCP", 1, "Verde", "Sporting", 19 },
                    { 2, null, "FCP", 1, "Azul", "Porto", 29 },
                    { 3, null, "SLB", 1, "Vermelho", "Benfica", 35 },
                    { 4, null, "CHE", 2, "Azul", "Chelsea", 3 },
                    { 5, null, "MCI", 2, "Azul", "Manchester City", 6 },
                    { 6, null, "MUN", 2, "Vermelho", "Manchester United", 35 },
                    { 7, null, "LIV", 2, "Vermelho", "Liverpool", 35 }
                });

            migrationBuilder.InsertData(
                table: "Player",
                columns: new[] { "PlayerId", "DateOfBirth", "MarketValue", "Name", "TeamId" },
                values: new object[,]
                {
                    { 1, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 25000000m, "Pote", 1 },
                    { 2, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 15000000m, "Paulinho", 1 },
                    { 3, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 10000000m, "Francisco Conceição", 2 },
                    { 4, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 25000000m, "Luis Diaz", 2 },
                    { 5, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 25000000m, "Rafa", 3 },
                    { 6, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 20000000m, "Grimaldo", 3 },
                    { 7, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 45000000m, "Lukaku", 4 },
                    { 8, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 25000000m, "Harvertz", 4 },
                    { 9, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 100000000m, "De Bruyne", 5 },
                    { 10, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 45000000m, "Bernardo Silva", 5 },
                    { 11, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 75000000m, "Ronaldo", 6 },
                    { 12, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 80000000m, "Bruno Fernandes", 6 },
                    { 13, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 200000000m, "Salah", 7 },
                    { 14, new DateTime(1990, 3, 22, 0, 0, 0, 0, DateTimeKind.Unspecified), 50000000m, "Diogo Jota", 7 }
                });

            migrationBuilder.CreateIndex(
                name: "IX_Player_TeamId",
                table: "Player",
                column: "TeamId");

            migrationBuilder.CreateIndex(
                name: "IX_Team_LeagueId",
                table: "Team",
                column: "LeagueId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Player");

            migrationBuilder.DropTable(
                name: "Team");

            migrationBuilder.DropTable(
                name: "League");
        }
    }
}
