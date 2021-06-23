package com.example.shawnapp;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Quote {
    private final String quote;
    private final String author;
    private static ArrayList<Quote> quoteList;

    public static ArrayList<Quote> getListQuote(){
        if(quoteList == null) {
            quoteList = initListQuote();
            Log.d("checkHam", "checkHam");
        }
        return quoteList;
    }

    private static ArrayList<Quote> initListQuote() {
        ArrayList<Quote> list = new ArrayList<>();
        list.add(new Quote("Life is about making an impact, not making an income.","-Kevin Kruse-"));
        list.add(new Quote("Whatever the mind of man can conceive and believe, it can achieve.","-Napoleon Hill-"));
        list.add(new Quote("Every strike brings me closer to the next home run.","-Babe Ruth-"));
        list.add(new Quote("Definiteness of purpose is the starting point of all achievement.","-W. Clement Stone-"));
        list.add(new Quote("Explore, Dream, Discover.","-Mark Twain-"));
        list.add(new Quote("Your time is limited, so don’t waste it living someone else’s life.","-Steve Jobs-"));
        list.add(new Quote("Winning isn’t everything, but wanting to win is.","-Vince Lombardi-"));
        list.add(new Quote("I am not a product of my circumstances. I am a product of my decisions.","-Stephen Covey-"));
        list.add(new Quote("Every child is an artist.  The problem is how to remain an artist once he grows up.","-Pablo Picasso-"));
        list.add(new Quote("You can never cross the ocean until you have the courage to lose sight of the shore.","-Christopher Columbus-"));
        list.add(new Quote("Either you run the day, or the day runs you.","-Jim Rohn-"));
        list.add(new Quote("Whether you think you can or you think you can’t, you’re right.","-Henry Ford-"));
        list.add(new Quote("The two most important days in your life are the day you are born and the day you find out why.","-Mark Twain-"));
        list.add(new Quote("Whatever you can do, or dream you can, begin it. Boldness has genius, power and magic in it.","-Johann Wolfgang von Goethe-"));
        list.add(new Quote("Life shrinks or expands in proportion to one's courage.","-Anais Nin-"));
        list.add(new Quote("If you hear a voice within you say “you cannot paint,” then by all means paint and that voice will be silenced.","-Vincent Van Gogh-"));
        list.add(new Quote("The only person you are destined to become is the person you decide to be.","-Ralph Waldo Emerson-"));
        list.add(new Quote("When I stand before God at the end of my life, I would hope that I would not have a single bit of talent left and could say, I used everything you gave me.","-Erma Bombeck-"));
        list.add(new Quote("Certain things catch your eye, but pursue only those that capture the heart.","- Ancient Indian Proverb-"));
        list.add(new Quote("Believe you can and you’re halfway there.","-Theodore Roosevelt-"));
        list.add(new Quote("Everything you’ve ever wanted is on the other side of fear.","-George Addair-"));
        list.add(new Quote("Start where you are. Use what you have.  Do what you can.","-Arthur Ashe-"));
        list.add(new Quote("Fall seven times and stand up eight.","-Japanese Proverb-"));
        list.add(new Quote("When one door of happiness closes, another opens, but often we look so long at the closed door that we do not see the one that has been opened for us.","-Helen Keller-"));
        list.add(new Quote("When I let go of what I am, I become what I might be.","-Lao Tzu-"));
        list.add(new Quote("We must believe that we are gifted for something, and that this thing, at whatever cost, must be attained.","-Marie Curie-"));
        list.add(new Quote("Too many of us are not living our dreams because we are living our fears.","-Les Brown-"));
        list.add(new Quote("Challenges are what make life interesting and overcoming them is what makes life meaningful.","-Joshua J. Marine-"));
        list.add(new Quote("If you want to lift yourself up, lift up someone else.","-Booker T. Washington-"));
        list.add(new Quote("I have been impressed with the urgency of doing. Knowing is not enough; we must apply. Being willing is not enough; we must do.","-Leonardo da Vinci-"));
        list.add(new Quote("I didn’t fail the test. I just found 100 ways to do it wrong.","-Benjamin Franklin-"));
        list.add(new Quote("In order to succeed, your desire for success should be greater than your fear of failure.","-Bill Cosby-"));
        list.add(new Quote("A person who never made a mistake never tried anything new.","- Albert Einstein-"));
        list.add(new Quote("There are no traffic jams along the extra mile.","-Roger Staubach-"));
        list.add(new Quote("It is never too late to be what you might have been.","-George Eliot-"));
        list.add(new Quote("You become what you believe."," -Oprah Winfrey-"));
        list.add(new Quote("I would rather die of passion than of boredom.","-Vincent van Gogh-"));
        list.add(new Quote("Build your own dreams, or someone else will hire you to build theirs.","-Farrah Gray-"));
        list.add(new Quote("The battles that count aren't the ones for gold medals. The struggles within yourself--the invisible battles inside all of us--that's where it's at.","-Jesse Owens-"));
        list.add(new Quote("I have learned over the years that when one's mind is made up, this diminishes fear.","-Rosa Parks-"));
        list.add(new Quote("If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough.","-Oprah Winfrey-"));
        list.add(new Quote("You can’t use up creativity.  The more you use, the more you have.","-Maya Angelou-"));
        list.add(new Quote("Dream big and dare to fail.","-Norman Vaughan-"));
        list.add(new Quote("Do what you can, where you are, with what you have.","-Teddy Roosevelt-"));
        list.add(new Quote("If you do what you’ve always done, you’ll get what you’ve always gotten.","-Tony Robbins-"));
        list.add(new Quote("Dreaming, after all, is a form of planning.","-Gloria Steinem-"));
        list.add(new Quote("It's your place in the world; it's your life. Go on and do all you can with it, and make it the life you want to live.","-Mae Jemison-"));
        list.add(new Quote("It’s not the years in your life that count. It’s the life in your years.","-Abraham Lincoln-"));
        list.add(new Quote("The only way to do great work is to love what you do.","-Steve Jobs-"));
        list.add(new Quote("If you can dream it, you can achieve it.","-Zig Ziglar-"));
        list.add(new Quote("I'm convinced that about half of what separates the successful entrepreneurs from the non-successful ones is pure perseverance.","-Steve Jobs-"));
        list.add(new Quote("Have the courage to follow your heart and intuition. They somehow know what you truly want to become.","-Steve Jobs-"));
        list.add(new Quote("Let's go invent tomorrow rather than worrying about what happened yesterday.","-Steve Jobs-"));
        list.add(new Quote("If today were the last day of your life, would you want to do what you are about to do today?","-Steve Jobs-"));
        list.add(new Quote("Life is about creating and living experiences that are worth sharing.","-Steve Jobs-"));
        list.add(new Quote("Be a yardstick of quality. Some people aren't used to an environment where excellence is expected.","-Steve Jobs-"));
        list.add(new Quote("Life repeats itself mindlessly – unless you become mindful, it will go on repeating like a wheel.","-Osho-"));
        list.add(new Quote("Be realistic: Plan for a miracle.","-Osho-"));
        list.add(new Quote("Get out of your head and get into your heart. Think less, feel more.","-Osho-"));
        return list;
    }

    private Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }



    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}
