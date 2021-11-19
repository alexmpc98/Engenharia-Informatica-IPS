using ESTeCard.Models;
using ESTeCard.Models.Data;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ESTeCard.Controllers
{
    public class Cards : Controller
    {
        private List<PrePaidCard> cards = EstCards.ESTeCards();
        public IActionResult Index()
        {
            return View(cards);
        }

        [HttpPost]
        public IActionResult Index(long? submitId)
        {
            List<PrePaidCard> cardsWithId = new List<PrePaidCard>();
            foreach (PrePaidCard card in cards)
            {
                if(card.id == submitId) {
                   cardsWithId.Add(card);
                }
                
            }
            return View(cardsWithId);      
        }
        public IActionResult Create()
        {
            return View();
        }
        [HttpPost]
        public IActionResult Create(PrePaidCard card)
        {
            if (card != null)
            {
                Random rnd = new Random();
                card.code = rnd.Next(0, 9999);
                cards.Add(card);
            }
            return RedirectToAction("Index");
        }
        
        public IActionResult Edit(long? id)
        {
            PrePaidCard cardView = new PrePaidCard();
            foreach (PrePaidCard card in cards)
            {
                if (card.id == id)
                {
                    cardView = card;
                }

            }
            return View(cardView);
        }
        [HttpPost]
        public IActionResult Edit(long id, PrePaidCard card)
        {
            foreach(PrePaidCard newCard in cards)
            {
                if(newCard.id == id)
                {
                    newCard.name = card.name;
                    newCard.code = card.code;
                    newCard.entity = card.entity;
                    newCard.credit = card.credit;
                }
            }
            return RedirectToAction("Index");
        }

    }
}
