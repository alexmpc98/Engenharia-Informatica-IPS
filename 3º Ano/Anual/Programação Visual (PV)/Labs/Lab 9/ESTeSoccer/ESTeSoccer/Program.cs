using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using ESTeSoccer.Data;
var builder = WebApplication.CreateBuilder(args);

builder.Services.AddDbContext<ESTeSoccerContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("ESTeSoccerContext")));

// Add services to the container.
builder.Services.AddControllersWithViews();

// Adicionar Swagger
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Adicionar Swagger
app.UseSwaggerUI();
app.UseSwagger();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();



}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
