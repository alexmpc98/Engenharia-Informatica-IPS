using System;
using Microsoft.EntityFrameworkCore.Migrations;

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
                    LeagueId = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Name = table.Column<string>(maxLength: 20, nullable: false),
                    Country = table.Column<string>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_League", x => x.LeagueId);
                });

            migrationBuilder.CreateTable(
                name: "Team",
                columns: table => new
                {
                    TeamId = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    LeagueId = table.Column<int>(nullable: false),
                    Name = table.Column<string>(nullable: false),
                    Initials = table.Column<string>(maxLength: 3, nullable: false),
                    FoundingDate = table.Column<DateTime>(nullable: false),
                    NumberOfTitles = table.Column<int>(nullable: false),
                    MainColor = table.Column<string>(nullable: true)
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
                    Playerid = table.Column<int>(nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    TeamId = table.Column<int>(nullable: false),
                    Name = table.Column<string>(nullable: false),
                    DateOfBirth = table.Column<DateTime>(nullable: false),
                    MarketValue = table.Column<double>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Player", x => x.Playerid);
                    table.ForeignKey(
                        name: "FK_Player_Team_TeamId",
                        column: x => x.TeamId,
                        principalTable: "Team",
                        principalColumn: "TeamId",
                        onDelete: ReferentialAction.Cascade);
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
                    { 1, new DateTime(1896, 5, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "FCP", 1, "Azul", "Porto", 40 },
                    { 2, new DateTime(1904, 5, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "SLB", 1, "Vermelho", "Benfica", 40 },
                    { 3, new DateTime(1906, 5, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "SCP", 1, "Verde", "Sporting", 40 },
                    { 5, new DateTime(1906, 5, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "MAN", 2, "Vermelho", "Manchester United", 100 },
                    { 6, new DateTime(1906, 5, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "MCT", 2, "azul", "Manchester City", 100 },
                    { 7, new DateTime(1906, 5, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "CHL", 2, "azul", "Chelsea", 100 },
                    { 4, new DateTime(1906, 5, 4, 0, 0, 0, 0, DateTimeKind.Unspecified), "LFP", 2, "Vermelho", "Liverpool", 100 }
                });

            migrationBuilder.InsertData(
                table: "Player",
                columns: new[] { "Playerid", "DateOfBirth", "MarketValue", "Name", "TeamId" },
                values: new object[,]
                {
                    { 1, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Alex", 1 },
                    { 2, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Alex2", 1 },
                    { 3, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Figo", 2 },
                    { 4, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Figo2", 2 },
                    { 5, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Ronaldo", 3 },
                    { 6, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Ronaldo2", 3 },
                    { 9, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Cristiano Ronaldo", 5 },
                    { 10, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Cristiano Ronaldo2", 5 },
                    { 11, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Bernardo Silva", 6 },
                    { 12, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Bernardo Silva2", 6 },
                    { 13, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Jorginho", 7 },
                    { 14, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Jorginho2", 7 },
                    { 7, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Salah", 4 },
                    { 8, new DateTime(1998, 6, 27, 0, 0, 0, 0, DateTimeKind.Unspecified), 2.0, "Salah2", 4 }
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
