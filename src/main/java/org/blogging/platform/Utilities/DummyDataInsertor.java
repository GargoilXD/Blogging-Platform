package org.blogging.platform.Utilities;

import org.blogging.platform.Context;
import org.blogging.platform.DataAccessors.Exceptions.DataAccessException;
import org.blogging.platform.DataTransporters.*;

import java.util.ArrayList;
import java.util.List;

public class DummyDataInsertor {
    List<UserDataTransporter> dummyUsers = List.of(
            new UserDataTransporter()
                    .setUsername("Kobby")
                    .setPassword("12345")
                    .setFullName("Kwabena Edusei")
                    .setEmail("kobby@gmail.com")
                    .setGender("M"),
            new UserDataTransporter()
                    .setUsername("Ama")
                    .setPassword("54321")
                    .setFullName("Ama Asante")
                    .setEmail("ama@gmail.com")
                    .setGender("F"),
            new UserDataTransporter()
                    .setUsername("Kwame")
                    .setPassword("12345")
                    .setFullName("Kwame Nkrumah")
                    .setEmail("kwame@example.com")
                    .setGender("M"),
            new UserDataTransporter()
                    .setUsername("Efia")
                    .setPassword("54321")
                    .setFullName("Efia Darko")
                    .setEmail("efia@example.com")
                    .setGender("F"),
            new UserDataTransporter()
                    .setUsername("Kofi")
                    .setPassword("12345")
                    .setFullName("Kofi Mensah")
                    .setEmail("kofi@example.com")
                    .setGender("M"),
            new UserDataTransporter()
                    .setUsername("Adwoa")
                    .setPassword("54321")
                    .setFullName("Adwoa Boateng")
                    .setEmail("adwoa@example.com")
                    .setGender("F"),
            new UserDataTransporter()
                    .setUsername("Yaw")
                    .setPassword("12345")
                    .setFullName("Yaw Osei")
                    .setEmail("yaw@example.com")
                    .setGender("M")
    );
    List<PostDataTransporter> dummyPosts = List.of(
            new PostDataTransporter()
                    .setUserID(1)
                    .setTitle("The 5-Minute Rule That Actually Works")
                    .setBody("""
                        We've all been there: a daunting list of small tasks that we keep rescheduling. "Reply to that email," "Clear the kitchen counter," "File that document." They linger, causing low-grade stress.
                        Enter the 5-minute rule. It's simple: if a task will take less than five minutes, do it immediately. Don't write it down. Don't mentally schedule it for later. Just do it now.
                        The magic isn't just in completing tiny tasks. It's in the momentum. Crossing off three quick things in 15 minutes can fuel the motivation to tackle a bigger project. It clears mental clutter, making space for focused work.
                        Try it for a day. You might be shocked by how light your to-do list feels by sunset."""),
            new PostDataTransporter()
                    .setUserID(2)
                    .setTitle("My Go-To \"Empty Fridge\" Pasta")
                    .setBody("""
                        You stare into the fridge. There's a half-used onion, a wilting bunch of herbs, a lemon, and the eternal block of parmesan. It's not a crisis it's an opportunity for aglio e olio.
                        This classic Roman pasta needs only pantry staples: garlic, red pepper flakes, olive oil, and spaghetti. It's a lesson in simplicity. The key is to infuse the oil slowly over low heat, toasting the garlic and chili without burning. Reserve a cup of the starchy pasta water before draining this, along with a vigorous stir, creates the silky, emulsified sauce.
                        Toss in your sad herbs for freshness and a squeeze of that lemon for brightness. Finish with a mountain of grated parmesan. Dinner is served, from "nothing" to something sublime."""),
            new PostDataTransporter()
                    .setUserID(1)
                    .setTitle("Is AI Your Co-pilot or Your Autopilot?")
                    .setBody("""
                        AI tools are everywhere now, promising to write our emails, generate our reports, and even craft our ideas. It's tempting to switch to autopilot and let the tool do the work.
                        But the real power lies in treating AI as a co-pilot. You still hold the controls. You provide the direction, the critical thinking, and the final judgment. Use AI to brainstorm first drafts, overcome writer's block, or summarize complex information. Then, take the output and make it yours edit, refine, and add your unique voice and insight.
                        The tool handles the heavy lifting; you steer toward the destination. Don't just accept what it gives you. Challenge it, guide it, and collaborate with it. That's where the magic happens."""),
            new PostDataTransporter()
                    .setUserID(2)
                    .setTitle("The Joy of a Screen-Free Hour")
                    .setBody("""
                        I challenged myself to one hour with no screens: no phone, no laptop, no TV. The first ten minutes were filled with phantom buzzes and a nagging sense of "I should be checking something."
                        Then, something shifted. I picked up a novel I'd been meaning to finish. I made a detailed grocery list on actual paper. I just sat with a cup of tea and watched the birds in the garden. The constant, low hum of digital anxiety faded.
                        It wasn't revolutionary, but it was restorative. In that quiet space, my thoughts felt less scattered. My focus deepened. I'm not swearing off technology, but I am carving out a little more of that analog space each day. Try it. Your notifications will wait."""),
            new PostDataTransporter()
                    .setUserID(1)
                    .setTitle("Found: The Perfect Bench")
                    .setBody("""
                        Every neighborhood has one a spot that feels uniquely yours. I found mine on a forgotten side path in the local park. It's a simple green wooden bench, slightly weathered, facing a small, burbling creek that most people miss.
                        On my lunch break, I'll sit there for just fifteen minutes. No agenda. I watch the water ripple over stones, listen to the leaves rustle, and feel the sun through the branches. It's a daily reset button.
                        We chase grand adventures, but sometimes peace is hidden in plain sight. I urge you to find your bench, your corner, your quiet stretch of path. Claim it. Visit it often. These small anchors make a city feel like home."""),
            new PostDataTransporter()
                    .setUserID(3)
                    .setTitle("Building a Morning Routine That Sticks")
                    .setBody("The key to a successful morning routine isn't complexity—it's consistency. Start with just two non-negotiables: hydration and movement. Drink a glass of water before checking your phone, and do five minutes of stretching or walking. These tiny anchors create a foundation that naturally expands over time. Within weeks, you'll find yourself adding journaling, meditation, or planning without feeling overwhelmed."),
            new PostDataTransporter()
                    .setUserID(4)
                    .setTitle("How I Learned to Stop Scrolling and Start Living")
                    .setBody("I deleted all social media apps for 30 days. The first week was withdrawal—boredom, FOMO, phantom vibrations. By week two, my mind felt quieter. I started reading again, calling friends instead of liking their posts, and noticing details in my neighborhood I'd missed for years. I didn't quit social media forever, but now I use it intentionally—not compulsively."),
            new PostDataTransporter()
                    .setUserID(5)
                    .setTitle("The Power of \"No\" in a World of Yes")
                    .setBody("Saying 'no' used to make me anxious. I feared missing opportunities or disappointing people. But I've learned that every 'yes' to something unimportant is a 'no' to what truly matters—my time, energy, and peace. Now, I pause before answering requests. If it doesn't align with my priorities, I decline gracefully. My relationships have deepened, not suffered."),
            new PostDataTransporter()
                    .setUserID(6)
                    .setTitle("My Grandmother's Plantain Recipe")
                    .setBody("Peel ripe plantains and slice them diagonally. Fry in coconut oil until golden on both sides. Sprinkle with sea salt and a dash of cinnamon while hot. Serve with a dollop of Greek yogurt. This simple dish connects me to childhood Sundays at my grandmother's kitchen in Kumasi. Food is memory, and this recipe is love on a plate."),
            new PostDataTransporter()
                    .setUserID(7)
                    .setTitle("Why Walking Is the Ultimate Productivity Hack")
                    .setBody("Forget expensive standing desks or productivity apps. The most effective tool I've found is a 20-minute walk—no podcast, no music, just moving. Ideas flow, problems untangle, and stress dissolves. Neuroscience shows rhythmic movement boosts creative thinking. Try it after lunch or when stuck on a problem. Your brain will thank you."),
            new PostDataTransporter()
                    .setUserID(3)
                    .setTitle("Embracing the Messy Middle of Creative Work")
                    .setBody("We celebrate finished projects but rarely talk about the messy middle—the phase where everything feels wrong, unclear, and frustrating. That's where growth happens. Instead of quitting, I've learned to lean into the discomfort. I set a timer for 25 minutes and commit to working through the fog. Almost always, clarity emerges on the other side."),
            new PostDataTransporter()
                    .setUserID(4)
                    .setTitle("Digital Minimalism: Less Tech, More Life")
                    .setBody("I unsubscribed from 50 newsletters, turned off all non-essential notifications, and moved apps off my home screen. The result? Two extra hours of focused time each day. Digital minimalism isn't about rejecting technology—it's about reclaiming attention. Ask yourself: does this tool serve my values, or distract from them?"),
            new PostDataTransporter()
                    .setUserID(5)
                    .setTitle("The Art of Asking for Help")
                    .setBody("Asking for help used to feel like weakness. Now I see it as collaboration. Last week, I struggled with a coding problem for hours. Finally, I messaged a colleague. In 10 minutes, we solved it together—and I learned a new approach. Vulnerability builds trust. Don't let pride steal your progress."),
            new PostDataTransporter()
                    .setUserID(6)
                    .setTitle("Rainy Day Comfort: My Go-To Soup")
                    .setBody("Chop onions, carrots, and celery. Sauté in olive oil until soft. Add diced tomatoes, vegetable broth, and a can of white beans. Simmer for 20 minutes. Finish with chopped kale and a squeeze of lemon. This soup costs less than $5, feeds four, and tastes like a hug. Perfect for gray afternoons when you need warmth from the inside out."),
            new PostDataTransporter()
                    .setUserID(7)
                    .setTitle("Finding Focus in a Distracted World")
                    .setBody("Deep work is a superpower. To cultivate it, I block 90-minute focus sessions in my calendar. During these, my phone is in another room, email is closed, and I work on one meaningful task. It's not easy—but the output in those 90 minutes often exceeds a full day of fragmented work. Protect your attention fiercely; it's your most valuable resource.")
    );
    List<CommentDataTransporter> dummyComments = List.of(
            new CommentDataTransporter().setUserID(1).setUsername("Kobby").setPostID(1).setBody("Great tip! I've been using this for a week and it really helps."),
            new CommentDataTransporter().setUserID(2).setUsername("Ama").setPostID(1).setBody("Does this work for bigger tasks too?"),
            new CommentDataTransporter().setUserID(1).setUsername("Kobby").setPostID(2).setBody("Love this recipe! Added some mushrooms and it turned out amazing."),
            new CommentDataTransporter().setUserID(2).setUsername("Ama").setPostID(3).setBody("Interesting perspective on AI collaboration."),
            new CommentDataTransporter().setUserID(2).setUsername("Ama").setPostID(4).setBody("I need to try this digital detox!"),
            new CommentDataTransporter().setUserID(1).setUsername("Kobby").setPostID(5).setBody("Found my own bench yesterday. Thanks for the inspiration!"),
            new CommentDataTransporter().setUserID(1).setUsername("Kobby").setPostID(6).setBody("This resonates so much! I've been trying to build a consistent morning routine too."),
            new CommentDataTransporter().setUserID(2).setUsername("Ama").setPostID(6).setBody("Do you use a journal? What kind?"),
            new CommentDataTransporter().setUserID(3).setUsername("Kwame").setPostID(7).setBody("I did a similar social media detox last year. Life-changing!"),
            new CommentDataTransporter().setUserID(4).setUsername("Efia").setPostID(8).setBody("So true about saying no. It's liberating once you get past the guilt."),
            new CommentDataTransporter().setUserID(5).setUsername("Kofi").setPostID(9).setBody("Your plantain recipe sounds delicious! Do you use overripe plantains?"),
            new CommentDataTransporter().setUserID(6).setUsername("Adwoa").setPostID(10).setBody("I walk daily now—best decision ever for mental clarity."),
            new CommentDataTransporter().setUserID(7).setUsername("Yaw").setPostID(11).setBody("The \"messy middle\" is real. Thanks for naming it!"),
            new CommentDataTransporter().setUserID(1).setUsername("Kobby").setPostID(12).setBody("Digital minimalism changed my life too. Started with turning off notifications."),
            new CommentDataTransporter().setUserID(2).setUsername("Ama").setPostID(13).setBody("Asking for help is hard but so necessary. Great reminder!"),
            new CommentDataTransporter().setUserID(3).setUsername("Kwame").setPostID(14).setBody("Made your soup last night—perfect for the rainy weather!"),
            new CommentDataTransporter().setUserID(4).setUsername("Efia").setPostID(15).setBody("I struggle with deep work. Any tips for avoiding the urge to check email?"),
            new CommentDataTransporter().setUserID(5).setUsername("Kofi").setPostID(2).setBody("Tried your pasta recipe—added mushrooms like you suggested. Amazing!"),
            new CommentDataTransporter().setUserID(6).setUsername("Adwoa").setPostID(4).setBody("Did your screen-free hour become a daily habit?"),
            new CommentDataTransporter().setUserID(7).setUsername("Yaw").setPostID(1).setBody("The 5-minute rule saved my productivity this week. Thank you!")
    );
    List<List<String>> dummyTags = List.of(
            List.of("Productivity", "LifeHacks", "Personal Growth"),
            List.of("Cooking", "Recipe", "Food"),
            List.of("AI", "Technology", "Tech"),
            List.of("Wellness", "Mindfulness", "Personal Growth", "Routine"),
            List.of("Local", "Nature", "Wellness"),
            List.of("Productivity", "LifeHacks", "Personal Growth"),
            List.of("Routine", "Personal Growth", "Mindfulness"),
            List.of("Personal Growth", "LifeHacks", "Wellness"),
            List.of("Cooking", "Recipe", "Food"),
            List.of("Productivity", "Wellness", "Nature"),
            List.of("Personal Growth", "LifeHacks", "Mindfulness"),
            List.of("Routine", "Personal Growth", "Wellness"),
            List.of("Personal Growth", "LifeHacks", "Wellness"),
            List.of("Cooking", "Recipe", "Food"),
            List.of("Productivity", "Personal Growth", "Mindfulness")
    );
    public void Insert(Context context) {
        try {
            for (UserDataTransporter transporter : dummyUsers) context.authenticationService.register(transporter);
            for (PostDataTransporter transporter : dummyPosts) context.postService.create(transporter.setIsDraft(false));
            for (CommentDataTransporter transporter : dummyComments) context.commentService.create(transporter);
            for (int index = 0; index < dummyTags.size(); index++) {
                context.tagService.setPostTags(index + 1, dummyTags.get(index));
            }
            System.out.println("Dummy Data Inserted!");
        }  catch (DataAccessException e) {
            System.err.printf("Error while inserting dummy data:%nError: %s%nCause: %s%n", e.getMessage(), e.getCause().getMessage());
        }
    }
    public void Remove(Context context) {
        try {
            for (UserDataTransporter transporter : dummyUsers) context.authenticationService.register(transporter);
            for (PostDataTransporter transporter : dummyPosts) context.postService.delete(transporter.getID());
            for (CommentDataTransporter transporter : dummyComments) context.commentService.delete(transporter.getID());
            for (int index = 0; index < dummyTags.size(); index++) {
                context.tagService.setPostTags(index + 1, new ArrayList<>());
            }
            System.out.println("Dummy Data Removed!");
        }  catch (DataAccessException e) {
            System.err.println("Error while removing dummy data: " + e.getMessage());
        }
    }
}
