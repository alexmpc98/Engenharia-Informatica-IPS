using Microsoft.AspNetCore.Identity;
using MiniESTS.Models;

namespace MiniESTS.Data
{
    public static class SeedData
    {
        public static async Task Seed(UserManager<Cliente> userManager, RoleManager<IdentityRole> roleManager)
        {
            await SeedRolesAsync(roleManager);
            await SeedUsersAsync(userManager);
        }

        private static async Task SeedRolesAsync(RoleManager<IdentityRole> roleManager)
        {
            var clientRole = new IdentityRole("cliente");
            if (!await roleManager.RoleExistsAsync(clientRole.Name))
            {
                await roleManager.CreateAsync(clientRole);
            }

            var adminsRole = new IdentityRole("administrador");
            if (!await roleManager.RoleExistsAsync(adminsRole.Name))
            {
                await roleManager.CreateAsync(adminsRole);
            }
        }

        private static async Task SeedUsersAsync(UserManager<Cliente> userManager)
        {
            if (userManager.FindByNameAsync("admin").Result == null)
            {
                var admin = new Cliente { Nome = "admin", PhoneNumber = "123456789", UserName = "admin@miniests.pt", Email = "admin@miniests.pt" };
                var result = await userManager.CreateAsync(admin, "123456");
                if (result.Succeeded)
                {
                    await userManager.AddToRoleAsync(admin, "administrador");
                }
            }

            if (userManager.FindByNameAsync("Jose").Result == null)
            {
                var jose = new Cliente { Nome = "Jose", PhoneNumber = "1234567890", UserName = "jose@mail.pt", Email = "jose@mail.pt" };
                var result1 = await userManager.CreateAsync(jose, "123456");
                if (result1.Succeeded)
                {
                    await userManager.AddToRoleAsync(jose, "cliente");
                }
            }

            if (userManager.FindByNameAsync("Alex").Result == null)
            {
                var alex = new Cliente { Nome = "Alex", PhoneNumber = "234567890", UserName = "alex@mail.com", Email = "alex@mail.com" };
                var result2 = await userManager.CreateAsync(alex, "123456");
                if (result2.Succeeded)
                {
                    await userManager.AddToRoleAsync(alex, "cliente");
                }
            }
        }
    }
}
